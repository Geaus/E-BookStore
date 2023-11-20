package com.example.microservice.Dao;

import com.example.microservice.Entity.Book;
import com.example.microservice.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao {
    @Autowired
    BookRepository bookRepository;

    public List<Book> findBookByNameContain(String str){
        return bookRepository.findBookByNameContaining(str);
    }

}
