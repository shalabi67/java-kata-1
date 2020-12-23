package org.echocat.kata.java.part1.authors;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="Authors")
public class Author {
    @Id
    private String email;
    private String firstName;
    private String lastName;

    public static Author create(String[] data) {
        //data format: email;firstname;lastname
        Author author = new Author();
        author.email = data[0];
        author.firstName = data[1];
        author.lastName = data[2];

        return author;
    }
}
