package org.echocat.kata.java.part1.items;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class MagazineInformation {
    @Id
    private String magazineIsbn;

    private LocalDate publishedAt;
}
