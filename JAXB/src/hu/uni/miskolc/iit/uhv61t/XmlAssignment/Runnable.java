package hu.uni.miskolc.iit.uhv61t.XmlAssignment;

import hu.uni.miskolc.iit.uhv61t.XmlAssignment.xjcModels.BookType;
import hu.uni.miskolc.iit.uhv61t.XmlAssignment.xjcModels.GenreType;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main entry point of the program.
 */
public class Runnable {

    /**
     * The controller, that manipulate data in database.
     */
    static Controller controller;

    public static void main(String[] args) throws JAXBException {
        controller = new Controller();
        int chosenMenu;

        do {
            printMenu();
            chosenMenu = readMenuChoice();
            switch (chosenMenu) {
                case 1:
                    BookType bookToAdd = getNewBook();
                    controller.createBook(bookToAdd);
                    printSuccess();
                    break;
                case 2:
                    BookType book = getBookByISBN();
                    printOutBook(book);
                    printSuccess();
                    break;
                case 3:
                    ArrayList<BookType> books = getBooksByAuthor();

                    if (books.isEmpty()) {
                        System.out.println("Book not found by the given author");
                        break;
                    }

                    for (BookType bookOfAuthor : books) {
                        printOutBook(bookOfAuthor);
                    }

                    printSuccess();
                    break;
                case 4:
                    BookType bookToModify = getNewBook();
                    controller.updateBook(bookToModify);
                    printSuccess();
                    break;
                case 5:
                    BookType bookToDelete = getBookByISBN();
                    controller.deleteBook(bookToDelete.getISBN());
                    printSuccess();
                    break;
            }
        } while (chosenMenu != 6);

        System.out.println();
        System.out.println("Exit ...");
    }

    /**
     * Prints the menu to stdout.
     */
    private static void printMenu () {
        System.out.println("1 - Add new book");
        System.out.println("2 - Get book data by ISBN");
        System.out.println("3 - Get books of author");
        System.out.println("4 - Modify book");
        System.out.println("5 - Delete book");
        System.out.println("6 - Quit from program");
        System.out.println("-------------------------");
        System.out.println("Choose menu: ");
    }

    /**
     * Reads the menu choice of the user from stdin.
     * @return
     */
    private static int readMenuChoice () {
        Scanner scanner = new Scanner(System.in);
        return Integer.valueOf(scanner.nextLine());
    }

    /**
     * Prints success message to stdout.
     */
    private static void printSuccess () {
        System.out.println();
        System.out.println("Success!");
        System.out.println();
    }

    /**
     * Reads data of new book from stdin.
     * @return The new book.
     */
    private static BookType getNewBook () {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ISBN: ");
        long ISBN = Long.valueOf(scanner.nextLine());

        System.out.println("Author: ");
        String author = String.valueOf(scanner.nextLine());

        System.out.println("Title: ");
        String title = String.valueOf(scanner.nextLine());

        System.out.println("Publish year: ");
        int year = Integer.valueOf(scanner.nextLine());

        System.out.println("Genre: ");
        String genre = String.valueOf(scanner.nextLine());

        BookType bookToAdd = new BookType();
        bookToAdd.setISBN("ISBN-" + String.valueOf(ISBN));
        bookToAdd.setAuthor(author);
        bookToAdd.setTitle(title);
        bookToAdd.setPublishYear(year);
        bookToAdd.setGenre(GenreType.fromValue(genre));

        return bookToAdd;
    }

    /**
     * Returns the book with the given ISBN.
     * @return Book with the given ISBN.
     */
    private static BookType getBookByISBN () {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ISBN: ");
        long isbn = Long.valueOf(scanner.nextLine());

        return controller.readBook(isbn);
    }

    /**
     * Prints out a book object to stdout.
     * @param book Book to print out.
     */
    private static void printOutBook (BookType book) {
        System.out.println();

        if (book == null) {
            System.out.println("Book not found with the given ISBN");
            return;
        }

        System.out.println("Book data:");
        System.out.println("ISBN: " + book.getISBN());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Publish year: " + book.getPublishYear());
        System.out.println("Genre: " + book.getGenre().value());

        System.out.println();
    }

    /**
     * Returns an array list with the books of an author.
     * @return Books of the author.
     */
    private static ArrayList<BookType> getBooksByAuthor () {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Author: ");
        String author = scanner.nextLine();

        return controller.readBooksByAuthor(author);
    }
}
