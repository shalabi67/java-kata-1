package org.echocat.kata.java.part1.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    //REG 4: Find all books and magazines by their authors’ email.
    List<Item> findAllByAuthors_email(String authorEmail);
}
