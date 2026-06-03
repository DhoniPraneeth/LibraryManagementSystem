package service;

import Model.Book;
import Model.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public class SearchResult<T>{
    List<LibraryItem> results=new ArrayList<>();
    String query;
    public List<LibraryItem> searchByAuthor(String author,LibraryCatalog<LibraryItem> items){
        return  items.getAll().stream()
                            .filter(x->x instanceof Book && ((Book)x).getAuthor().equals(author))
                            .toList();
    }
    public List<LibraryItem> searchByTitle(String title,LibraryCatalog<LibraryItem> items){
        return  items.getAll().stream()
                .filter(x->x instanceof Book && ((Book)x).getName().equals(title))
                .toList();
    }

    public List<LibraryItem> searchById(String itemId,LibraryCatalog<LibraryItem> items) {
        return items.getAll().stream()
                .filter(x->x instanceof Book && ((Book)x).getId().equals(itemId))
                .toList();
    }
}
