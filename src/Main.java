import Model.Book;
import service.LibraryService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner r=new Scanner(System.in);
        LibraryService service=new LibraryService();
        service.add("1", "The Alchemist", "Fiction", "Paulo Coelho", "9780062315007", true);
        service.add("2", "Harry Potter and the Sorcerer's Stone", "Fantasy", "J.K. Rowling", "9780590353427", false);
        service.add("3", "Wings of Fire", "Autobiography", "A.P.J. Abdul Kalam", "9788173711466", true);
        service.add("4", "The Hobbit", "Adventure", "J.R.R. Tolkien", "9780547928227", false);
        service.add("5", "Atomic Habits", "Self Help", "James Clear", "9780735211292", true);

        service.addMember("100","Praneeth","xyz123@gmail.com");
        service.addMember("101","Sai","abc456@gmail.com");
        service.addMember("103","Chandu","pqr789@gmail.com");
        while(true){
            System.out.println("Enter your input");
            String in=r.nextLine();
            switch(in){
                case "1":   System.out.println("\n---Books in Library Catalog---");
                                    service.getBooks().forEach(z-> System.out.println(z));
                                    break;
                case "2":   System.out.println("\n---Search By Author---");
                            service.searchByAuthor("Paulo Coelho").forEach(x-> System.out.println(x));
                            break;
                case "3":   System.out.println("\n---Available Books---");
                            service.getAvailableBooks().forEach(x-> System.out.println(x));;
                            break;
                case "4":   System.out.println("\n---Sorted List Items By Title---");
                            service.sortByTitle();
                            break;
                case "5":   System.out.println("\n---Memebers---");
                            service.getMemebers();
                            break;
                case "6":   System.out.println("Need to Borrow Book:");
                            System.out.println("Enter ur memberId");
                            String memberId=r.nextLine();
                            if(service.validateMember(memberId)){
                                System.out.println("Enter your Book Title");
                                String title=r.nextLine();
                                Book b=service.getBookByTitle(title,service.getAvailableBooks());
                                System.out.println(b);
                                System.out.println();
                                service.borrow(memberId,b);
                            }else{
                                System.out.println("Member Not exist.Please Register");
                            }
                            break;

            }
        }
    }
}