package com.example.microservice.Repository;

import com.example.microservice.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBookByNameContaining(String str);
}