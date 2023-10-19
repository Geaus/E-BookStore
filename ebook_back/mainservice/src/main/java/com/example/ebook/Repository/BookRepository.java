package com.example.ebook.Repository;

import com.example.ebook.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findBookByIdIs(int index);

    void deleteBookByIdIs(int index);

    List<Book> findBooksByNameContaining(String name);

    Book findBookByName(String name);
}
