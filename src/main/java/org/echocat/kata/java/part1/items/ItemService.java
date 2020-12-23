package org.echocat.kata.java.part1.items;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.echocat.kata.java.part1.authors.AuthorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@AllArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final AuthorRepository authorRepository;

    public Item addBook(String[] bookData) {
        return itemRepository.save(ItemFactory.createBook(bookData, authorRepository));
    }

    public Item addMagazine(String[] magazineData) {
        return itemRepository.save(ItemFactory.createMagazine(magazineData, authorRepository));
    }

    public void printItems(Supplier<List<Item>> supplier) {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        supplier.get().forEach(item -> print(objectWriter, item));
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public List<Item> getItemsSortedByTitle() {
        return itemRepository.findAll(Sort.by("title"));
    }

    public Optional<Item> getItem(String isbn) {
        return itemRepository.findById(isbn);
    }


    private void print(ObjectWriter objectWriter, Item item) {
        String stringItem;
        try {
            stringItem = objectWriter.writeValueAsString(item);
        } catch (JsonProcessingException e) {
            stringItem = item.toString();
        }

        System.out.println(stringItem);
    }
}
