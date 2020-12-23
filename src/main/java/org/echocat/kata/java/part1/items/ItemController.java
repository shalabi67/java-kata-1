package org.echocat.kata.java.part1.items;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    /*
    REQ 3: Find a book or magazine by its isbn. 
     */
    @GetMapping("/{isbn}")
    public ResponseEntity<Item> getItemByIsbn(@PathVariable String isbn) {
        return itemService.getItem(isbn)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
