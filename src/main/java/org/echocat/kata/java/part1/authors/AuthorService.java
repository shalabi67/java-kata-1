package org.echocat.kata.java.part1.authors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }
}
