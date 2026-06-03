package Model;

import Interface.Borrowable;

public class Book extends LibraryItem implements Borrowable {
    public String author;
    public String isbn;
    public Boolean available;

    @Override
    public String toString() {
        return "Name='"+super.getName()+'\''+
                "Genre='"+super.getGenre()+'\''+
                "author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", available=" + available ;
    }

    public void display(){

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getIsbn() {
        return isbn;
    }

    public Boolean getAvailable() {
        return available;
    }

    @Override
    public void borrow() {

    }

    @Override
    public void returnItem() {

    }

    @Override
    public boolean isAvailable() {
        return getAvailable();
    }
}
