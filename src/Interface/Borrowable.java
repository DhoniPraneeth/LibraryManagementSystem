package Interface;

import Model.Member;

public interface Borrowable {
    void borrow(Member memberById, String borrowedAt);
    void returnItem();
    boolean isAvailable();
}
