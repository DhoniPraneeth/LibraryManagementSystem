package service;

import Model.BorrowRequest;
import Model.Book;
import Model.LibraryItem;
import Model.Member;

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

    public void getAvailableBooks() {
        List<LibraryItem> res=libraryItems.list.stream()
                .filter(x->((Book)x).isAvailable())
                .toList();
        res.forEach(x-> System.out.println(x));
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
}
