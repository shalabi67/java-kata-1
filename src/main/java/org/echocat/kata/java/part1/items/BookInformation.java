package org.echocat.kata.java.part1.items;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
@Entity
public class BookInformation {
    @Id
    private String bookIsbn;

    @Lob
    private String description;
}
