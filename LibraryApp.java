import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {

    private static BookDAO bookDAO = new BookDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View Book");
            System.out.println("3. List All Books");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter published year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter genre: ");
                    String genre = scanner.nextLine();
                    Book newBook = new Book();
                    newBook.setTitle(title);
                    newBook.setAuthor(author);
                    newBook.setPublishedYear(year);
                    newBook.setGenre(genre);
                    try {
                        bookDAO.addBook(newBook);
                        System.out.println("Book added successfully!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.print("Enter book ID: ");
                    int id = scanner.nextInt();
                    try {
                        Book book = bookDAO.getBook(id);
                        if (book != null) {
                            System.out.println("ID: " + book.getId());
                            System.out.println("Title: " + book.getTitle());
                            System.out.println("Author: " + book.getAuthor());
                            System.out.println("Published Year: " + book.getPublishedYear());
                            System.out.println("Genre: " + book.getGenre());
                        } else {
                            System.out.println("Book not found.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        List<Book> books = bookDAO.getAllBooks();
                        for (Book b : books) {
                            System.out.println("ID: " + b.getId() + ", Title: " + b.getTitle() + ", Author: " + b.getAuthor());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.print("Enter book ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new author: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter new published year: ");
                    int newYear = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new genre: ");
                    String newGenre = scanner.nextLine();
                    Book updatedBook = new Book();
                    updatedBook.setId(updateId);
                    updatedBook.setTitle(newTitle);
                    updatedBook.setAuthor(newAuthor);
                    updatedBook.setPublishedYear(newYear);
                    updatedBook.setGenre(newGenre);
                    try {
                        bookDAO.updateBook(updatedBook);
                        System.out.println("Book updated successfully!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.print("Enter book ID to delete: ");
                    int deleteId = scanner.nextInt();
                    try {
                        bookDAO.deleteBook(deleteId);
                        System.out.println("Book deleted successfully!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
