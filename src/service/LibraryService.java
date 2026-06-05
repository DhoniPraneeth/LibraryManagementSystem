package service;

import LibraryException.BookNotAvailableException;
import Model.*;
import utils.DateFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LibraryService {
    LibraryCatalog<LibraryItem> libraryItems;
    Map<String, Member> members;
    Set<String> set;
    Queue<BorrowRequest> bQueue;
    DateFormatter date;

    @Override
    public String toString() {
        return "\nLibraryItems=\n" + libraryItems +"\n"+
                "\nMembers=\n" + members +"\n"+
                "\nSet=\n" + set +"\n"+
                "\nBQueue=\n" + bQueue +"\n"+
                "\nSearchResult=\n" + searchResult +"\n"+
                '\n';
    }

    SearchResult<Book> searchResult;
    public LibraryService() {
        this.libraryItems = new LibraryCatalog<>();
        this.members = new HashMap<>();
        this.set = new HashSet<>();
        this.bQueue = new ArrayDeque<>();
        this.searchResult=new SearchResult<>();
        this.date=new DateFormatter();
    }
    public Book add(String id, String bName, String genre, String author, String isbn, Boolean available) {
        Book item=new Book();
        item.setName(bName);
        item.setGenre(genre);
        item.setAuthor(author);
        item.setId(id);
        item.setIsbn(isbn);
        item.setAvailable(available);
        libraryItems.add(item);
       // libraryItems.list.forEach(x->System.out.println(x));
        return item;
    }
    public List<LibraryItem> searchByAuthor(String author){
        return searchResult.searchByAuthor(author,libraryItems);
    }
    public List<LibraryItem> getBooks(){
        return libraryItems.getAll();
    }

    public List<Book> getAvailableBooks() {
        return libraryItems.getAll().stream()
                .filter(x->x instanceof Book)
                .map(x->(Book)x)
                .filter(x->x.isAvailable())
                .toList();
    }

    public void sortByTitle() {
        libraryItems.getAll().stream()
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

    public Book searchByTitle(String title) {
        return (Book)searchResult.searchByTitle(title,libraryItems).getFirst();
    }

    public Member getMemberById(String memberId) {
        return members.get(memberId);
    }

    public void borrow(String memberId, Book b) throws BookNotAvailableException {
        Member member=members.get(memberId);
        String time= date.getDate();
        if(b.isAvailable()){
            BorrowedRecord br=new BorrowedRecord();
            br.setBorrowedAt(time);
            br.setBook(b);
            br.setMember(member);
            List<BorrowedRecord> l=member.getBooks();
            l.add(br);
            member.setBooks(l);
            b.setAvailable(false);
        }else{
            BorrowRequest br=new BorrowRequest();
            br.setBook(b);
            br.setMember(member);
            br.setRequestedAt(time);
            bQueue.add(br);
            throw new BookNotAvailableException("Book was not Available :)");
        }
    }

    public void returnBook(String memId, String title) {
        Member member=members.get(memId);
        member.getBooks().remove(title);
        String returnedAt= date.getDate();
        Book b= (Book) searchResult.searchByTitle(title,libraryItems);
        b.setAvailable(true);
        List<BorrowedRecord> borRecords= member.getBooks();
        BorrowedRecord record;
        for(BorrowedRecord temp:borRecords){
            if(temp.getBook().equals(title)){
                temp.setReturnedAt(returnedAt);
                break;
            }
        }
    }

}
