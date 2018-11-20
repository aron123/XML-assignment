package hu.uni.miskolc.iit.uhv61t.XmlAssignment;

import hu.uni.miskolc.iit.uhv61t.XmlAssignment.xjcModels.BookType;
import hu.uni.miskolc.iit.uhv61t.XmlAssignment.xjcModels.Database;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class does the data manipulation in XML database.
 */
class Controller {

    /**
     * Root element of the database XML.
     */
    private Database database;

    Controller() throws JAXBException {
        this.initializeDatabase();
    }

    /**
     * Instantiates a new Database object, and fills it with the data from XML.
     * @throws JAXBException
     */
    private void initializeDatabase () throws JAXBException {
        File xml = new File("resources/database.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Database.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        this.database = (Database) unmarshaller.unmarshal(xml);
    }

    /**
     * Writes out the changes that are done in database field.
     * @throws JAXBException
     */
    private void writeOutChanges () throws JAXBException {
        File xml = new File("resources/database.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Database.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); //pretty print

        marshaller.marshal(this.database, xml);
    }

    /**
     * Creates a new book in database.
     * @param bookToAdd The book to add to database.
     * @throws JAXBException
     */
    void createBook (BookType bookToAdd) throws JAXBException {
        this.database.getBooks().getBook().add(bookToAdd);
        this.writeOutChanges();
    }

    /**
     * Returns the book with the given ISBN.
     * @param isbn ISBN to search.
     * @return Books with the given ISBN.
     */
    BookType readBook (long isbn) {
        Collection<BookType> books = this.database.getBooks().getBook();
        String isbnToSearch = "ISBN-" + String.valueOf(isbn);

        for (BookType book : books) {
            if (book.getISBN().equals(isbnToSearch)) {
                return book;
            }
        }

        return null;
    }

    /**
     * Returns the books of the given author.
     * @param authorToSearch Author to search.
     * @return The authors' books.
     */
    ArrayList<BookType> readBooksByAuthor (String authorToSearch) {
        Collection<BookType> books = this.database.getBooks().getBook();
        ArrayList<BookType> results = new ArrayList<>();

        for (BookType book : books) {
            if (book.getAuthor().equals(authorToSearch)) {
                results.add(book);
            }
        }

        return results;
    }

    /**
     * Updates book based on its ISBN
     * @param bookToUpdate The book with the appropriate ISBN and new information.
     * @throws JAXBException
     */
    void updateBook (BookType bookToUpdate) throws JAXBException {
        Collection<BookType> books = this.database.getBooks().getBook();
        BookType found = null;

        for (BookType book : books) {
            if (book.getISBN().equals(bookToUpdate.getISBN())) {
                found = book;
            }
        }

        books.remove(found);
        books.add(bookToUpdate);
        this.writeOutChanges();
    }

    /**
     * Deletes the book with the given ISBN.
     * @param isbnToSearch The ISBN of book to delete.
     * @throws JAXBException
     */
    void deleteBook (String isbnToSearch) throws JAXBException {
        Collection<BookType> books = this.database.getBooks().getBook();

        for (BookType book : books) {
            if (book.getISBN().equals(isbnToSearch)) {
                books.remove(book);
            }
        }

        this.writeOutChanges();
    }
}
