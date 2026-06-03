package Model;

import java.util.List;

public class Member {
    private String memberId;
    private String name;
    private String email;

    public List<BorrowedRecord> getBooks() {
        return books;
    }

    public void setBooks(List<BorrowedRecord> books) {
        this.books = books;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "MemberId=" + memberId + '\n' +
                "Name=" + name + '\n' +
                "email=" + email + '\n' +
                "Books=" + books +
                '}'+'\n';
    }

    private List<BorrowedRecord> books;
}
