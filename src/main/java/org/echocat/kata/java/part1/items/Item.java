package org.echocat.kata.java.part1.items;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.echocat.kata.java.part1.authors.Author;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Items", indexes = {
        @Index(columnList = "title")
})
public class Item {
    @Id
    private String isbn;
    private String title;

    private ItemTypes type;

    //TODO: FetchType.EAGER will cause performance issues
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Author> authors = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "isbn", referencedColumnName = "magazineIsbn")
    private MagazineInformation magazineInformation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "isbn", referencedColumnName = "bookIsbn")
    private BookInformation bookInformation;
}
