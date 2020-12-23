package org.echocat.kata.java.part1.files;

import lombok.extern.slf4j.Slf4j;
import org.echocat.kata.java.part1.authors.Author;

import java.util.function.Consumer;

@Slf4j
public class DatabaseWriter {

    //TODO: remove me when done
    public static Consumer<String[]> LOGGER = data -> {
        log.info("-------------------------");
        for (String value : data) {
            log.info(value);
        }

    };

    public static  Consumer<String[]> getAuthor(Consumer<Author> writer) {
        return data -> {
            Author author = Author.create(data);
            writer.accept(author);
        };
    }
}
