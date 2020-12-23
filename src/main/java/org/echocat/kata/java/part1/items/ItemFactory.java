package org.echocat.kata.java.part1.items;

import lombok.extern.slf4j.Slf4j;
import org.echocat.kata.java.part1.authors.Author;
import org.echocat.kata.java.part1.authors.AuthorRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
public class ItemFactory {
    public static Item createBook(String[] data, AuthorRepository authorRepository) {
        //data format: title;isbn;authors;description
        String isbn = data[1];
        Item item = createItem(isbn, data[0], ItemTypes.Book);
        item.setBookInformation(createBookInformation(data[3], isbn));

        addAuthors(authorRepository, item, data[2]);

        return item;
    }

    public static Item createMagazine(String[] data, AuthorRepository authorRepository) {
        //data format: title;isbn;authors;publishedAt
        String isbn = data[1];
        Item item = createItem(isbn, data[0], ItemTypes.Magazine);
        item.setMagazineInformation(createMagazineInformation(data[3], isbn));

        addAuthors(authorRepository, item, data[2]);

        return item;
    }

    private static Item createItem(String isbn, String datum, ItemTypes book) {
        Item item = new Item();
        item.setIsbn(isbn);
        item.setTitle(datum);
        item.setType(book);

        return item;
    }

    private static MagazineInformation createMagazineInformation(String datum, String isbn) {
        MagazineInformation magazineInformation = new MagazineInformation();
        magazineInformation.setMagazineIsbn(isbn);
        magazineInformation.setPublishedAt(getDate(datum));

        return magazineInformation;
    }

    private static LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

        return LocalDate.parse(date, formatter);
    }

    private static BookInformation createBookInformation(String datum, String isbn) {
        BookInformation bookInformation = new BookInformation();
        bookInformation.setBookIsbn(isbn);
        bookInformation.setDescription(datum);
        return bookInformation;
    }

    private static void addAuthors(AuthorRepository authorRepository, Item item, String datum) {
        for (String authorEmail : datum.split(",")) {
            Optional<Author> author = authorRepository.findById(authorEmail);
            author.ifPresent(value -> item.getAuthors().add(value));
        }
    }

}
