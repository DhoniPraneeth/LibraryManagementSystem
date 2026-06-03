package service;

import Model.LibraryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryCatalog<T extends LibraryItem> {
    private List<T> list;

    public LibraryCatalog() {
        this.list = new ArrayList<>();
    }

    public void add(T item) {
        this.list.add(item);
    }

    public void remove(T item) {
        this.list.remove(item);
    }

    public List<T> getAll() {
        return list;
    }

    public Optional<T> findById(String id) {
        return list.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }
}
