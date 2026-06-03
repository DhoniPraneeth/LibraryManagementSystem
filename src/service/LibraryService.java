package service;

import Model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LibraryService {
    LibraryCatalog<LibraryItem> libraryItems;
    Map<String, Member> members;
    Set<String> set;
    Queue<BorrowRequest> bQueue;
    SearchResult<Book> searchResult;
    public LibraryService() {
        this.libraryItems = new LibraryCatalog<>();
        this.members = new HashMap<>();
        this.set = new HashSet<>();
        this.bQueue = new ArrayDeque<>();
        this.searchResult=new SearchResult<>();
    }
    public Book add(String id, String bName, String genre, String author, String isbn, Boolean available) {
        Book item=new Book();
        item.setName(bName);
        item.setGenre(genre);
        item.setAuthor(author);
        item.setId(id);
        item.setIsbn(isbn);
        item.setAvailable(available);
        libraryItems.list.add(item);
       // libraryItems.list.forEach(x->System.out.println(x));
        return item;
    }
    public List<LibraryItem> searchByAuthor(String author){
        return searchResult.searchByAuthor(author,libraryItems);
    }
    public List<LibraryItem> getBooks(){
        return libraryItems.list;
    }

    public List<LibraryItem> getAvailableBooks() {
        List<LibraryItem> res=libraryItems.list.stream()
                .filter(x->((Book)x).isAvailable())
                .toList();
        return res;
    }

    public void sortByTitle() {
        libraryItems.list.stream()
                .sorted(Comparator.comparing(LibraryItem::getName))
                .toList()
                .forEach(x-> System.out.println(x));
    }

    public void addMember(String id, String name, String mail){
        Member member=new Member();
        member.setMemberId(id);
        member.setName(name);
        member.setEmail(mail);
        member.setBooks(new ArrayList<>());
        members.put(id,member);
    }
    public void getMemebers(){
        members.forEach((x,y)-> System.out.println(y));
    }

    public boolean validateMember(String memberId) {
        return members.containsKey(memberId);
    }

    public Book getBookByTitle(String title,List<LibraryItem> itemList) {
        for(LibraryItem item: itemList){
            if(item.getName().equals(title)){
                System.out.println("got it");
                return (Book)item;
            }
        }
        return null;
    }

    public Member getMemberById(String memberId) {
        return members.get(memberId);
    }

    public void borrow(String memberId, Book b) {
        Member member=members.get(memberId);
        LocalDate date=LocalDate.now();
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String time=date.format(formatter);
        if(b!=null){
            BorrowedRecord br=new BorrowedRecord();
            br.setBorrowedAt(time);
            br.setBook(b);
            br.setMember(member);
            List<BorrowedRecord> l=member.getBooks();
            l.add(br);
            member.setBooks(l);
            b.setAvailable(false);
        }else{
            System.out.println("The item is borrowed by someone when it is available we notify you :)");
            BorrowRequest br=new BorrowRequest();
            br.setBook(b);
            br.setMember(member);
            br.setRequestedAt(time);
            bQueue.add(br);
        }
    }
}
