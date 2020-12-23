package org.echocat.kata.java.part1.files;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.echocat.kata.java.part1.authors.AuthorService;
import org.echocat.kata.java.part1.items.ItemService;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

@Slf4j
@AllArgsConstructor
@Service
public class TextFileProcessor {
    private static String PATH = "org/echocat/kata/java/part1/data/";
    private static Function<String, String[]> READER = data -> data.split(";");

    private final AuthorService authorService;
    private final ItemService itemService;

    public void saveCsvToDatabase(String resourcePath, Consumer<String[]> writer) {
        try {
            Stream<String> inputStream = connect(resourcePath);
            process(inputStream, READER, writer);
        }catch(Exception e) {
            log.error("Error while processing file {} error: {}", resourcePath, e.getMessage());
        }

    }

    @PostConstruct
    public void process() {
        //REQ 1: Your software should read all data from the given CSV files in a meaningful structure.
        log.info("REQ 1: DONE");
        saveCsvToDatabase(getFullPath("authors.csv"), DatabaseWriter.getAuthor(authorService::addAuthor));
        saveCsvToDatabase(getFullPath("books.csv"), itemService::addBook);
        saveCsvToDatabase(getFullPath("magazines.csv"), itemService::addMagazine);

        //REQ 2: Print out all books and magazines (could be a GUI, console, …) with all their details (with a meaningful output format).
        log.info("REQ 2: DONE");
        itemService.printItems(itemService::getItems);

        //REQ 5: Print out all books and magazines with all their details sorted by title. This sort should be done for books and magazines together.
        log.info("REQ 5: DONE");
        itemService.printItems(itemService::getItemsSortedByTitle);
    }

    private String getFullPath(String fileName) {
        return PATH + fileName;
    }

    private Stream<String> connect(String resourcePath) throws URISyntaxException, IOException {
        Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                .getResource(resourcePath)).toURI());

        return Files.lines(path).skip(1L);
    }

    private void process(Stream<String> stream, Function<String, String[]> processor, Consumer<String[]> writer) {
        stream
            .filter(data -> !data.isEmpty())
            .forEach(data -> {
                try {
                    String[] items = processor.apply(data);
                    writer.accept(items);
                } catch (Exception e) {
                    log.error("Data: {} Error: {}", data, e.getMessage());
                }
            });
    }
}
