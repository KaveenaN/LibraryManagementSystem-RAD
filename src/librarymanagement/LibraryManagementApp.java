package librarymanagement;

import java.util.Scanner;

public class LibraryManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add Books");
            System.out.println("2. Register Members");
            System.out.println("3. Remove Books");
            System.out.println("4. Remove Members");
            System.out.println("5. Search Book Information");
            System.out.println("6. Search Member Information");
            System.out.println("7. Display Book Names");
            System.out.println("8. Display Member Names");
            System.out.println("9. Lend Books");
            System.out.println("10. Return Books");
            System.out.println("11. View Lending Information");
            System.out.println("12. Display Overdue Books");
            System.out.println("13. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    library.addBook(bookTitle, author);
                    break;
                case 2:
                    System.out.print("Enter member name: ");
                    String memberName = scanner.nextLine();
                    library.registerMember(memberName);
                    break;
                case 3:
                    System.out.print("Enter book ID to remove: ");
                    int bookIdToRemove = scanner.nextInt();
                    library.removeBook(bookIdToRemove);
                    break;
                case 4:
                    System.out.print("Enter member ID to remove: ");
                    int memberIdToRemove = scanner.nextInt();
                    library.removeMember(memberIdToRemove);
                    break;
                case 5:
                    System.out.print("Enter book ID to search: ");
                    int bookIdToSearch = scanner.nextInt();
                    library.searchBookInformation(bookIdToSearch);
                    break;
                case 6:
                    System.out.print("Enter member ID to search: ");
                    int memberIdToSearch = scanner.nextInt();
                    library.searchMemberInformation(memberIdToSearch);
                    break;
                case 7:
                    System.out.println("Books in the library:");
                    library.displayBookNames();
                    break;
                case 8:
                    System.out.println("Registered members:");
                    library.displayMemberNames();
                    break;
                case 9:
                    System.out.print("Enter member ID: ");
                    int memberIdToLend = scanner.nextInt();
                    System.out.print("Enter book ID to lend: ");
                    int bookIdToLend = scanner.nextInt();
                    System.out.print("Enter number of days to return: ");
                    int daysToReturn = scanner.nextInt();
                    library.lendBook(memberIdToLend, bookIdToLend, daysToReturn);
                    break;
                case 10:
                    System.out.print("Enter transaction ID to return: ");
                    int transactionIdToReturn = scanner.nextInt();
                    library.returnBook(transactionIdToReturn);
                    break;
                case 11:
                    System.out.println("Lending Information:");
                    library.viewLendingInformation();
                    break;
                case 12:
                    System.out.println("Overdue Books:");
                    library.displayOverdueBooks();
                    break;
                case 13:
                    System.out.println("Exiting Library Management System.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
