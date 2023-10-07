package librarymanagement;

import java.util.*;
import java.text.SimpleDateFormat;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private List<Transaction> transactions;
    private int bookIdCounter;
    private int memberIdCounter;
    private int transactionIdCounter;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        transactions = new ArrayList<>();
        bookIdCounter = 1;
        memberIdCounter = 1;
        transactionIdCounter = 1;
    }

    public void addBook(String title, String author) {
        Book book = new Book(bookIdCounter++, title, author);
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void registerMember(String name) {
        Member member = new Member(memberIdCounter++, name);
        members.add(member);
        System.out.println("Member registered successfully.");
    }

    public void removeBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                books.remove(book);
                System.out.println("Book removed successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void removeMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                members.remove(member);
                System.out.println("Member removed successfully.");
                return;
            }
        }
        System.out.println("Member not found.");
    }

    public void searchBookInformation(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                System.out.println(book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void searchMemberInformation(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                System.out.println(member);
                return;
            }
        }
        System.out.println("Member not found.");
    }

    public void displayBookNames() {
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }

    public void displayMemberNames() {
        for (Member member : members) {
            System.out.println(member.getName());
        }
    }

    public void lendBook(int memberId, int bookId, int daysToReturn) {
        for (Book book : books) {
            if (book.getBookId() == bookId && book.isAvailable()) {
                for (Member member : members) {
                    if (member.getMemberId() == memberId) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.DATE, daysToReturn);
                        Date dueDate = calendar.getTime();
                        Transaction transaction = new Transaction(transactionIdCounter++, memberId, bookId, dueDate);
                        transactions.add(transaction);
                        book.borrowBook();
                        System.out.println("Book lent successfully. Due Date: " + dueDate);
                        return;
                    }
                }
            }
        }
        System.out.println("Book not available or member not found.");
    }

    public void returnBook(int transactionId) {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionId() == transactionId) {
                Date dueDate = transaction.getDueDate();
                long fine = calculateFine(dueDate);

                if (fine > 0) {
                    System.out.println("Book returned late. Fine: Rs. " + fine);
                } else {
                    System.out.println("Book returned on time. No fine.");
                }

                for (Book book : books) {
                    if (book.getBookId() == transaction.getBookId()) {
                        book.returnBook();
                        transactions.remove(transaction);
                        System.out.println("Book returned successfully.");
                        return;
                    }
                }
            }
        }
        System.out.println("Transaction not found.");
    }

    public void viewLendingInformation() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public void displayOverdueBooks() {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for (Transaction transaction : transactions) {
            if (currentDate.after(transaction.getDueDate())) {
                System.out.println("Book ID: " + transaction.getBookId() + ", Member ID: " +
                                   transaction.getMemberId() + ", Due Date: " + sdf.format(transaction.getDueDate()));
            }
        }
    }

    // Add this method to the Library class
    public long calculateFine(Date dueDate) {
        Date currentDate = new Date();
        long overdueDays = (currentDate.getTime() - dueDate.getTime()) / (24 * 60 * 60 * 1000);

        if (overdueDays <= 0) {
            return 0; // No fine if returned on or before due date
        } else if (overdueDays <= 7) {
            return overdueDays * 50;
        } else {
            return 7 * 50 + (overdueDays - 7) * 100;
        }
    }
}
