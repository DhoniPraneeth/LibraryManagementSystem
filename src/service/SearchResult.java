package service;

import Model.Book;
import Model.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public class SearchResult<T>{
    List<LibraryItem> results=new ArrayList<>();
    String query;
    public List<LibraryItem> searchByAuthor(String author,LibraryCatalog<LibraryItem> items){
        results= (List<LibraryItem>) items.list.stream()
                                        .filter(x->((Book)x).getAuthor().equals(author))
                                        .toList();
        return results;
    }
}
