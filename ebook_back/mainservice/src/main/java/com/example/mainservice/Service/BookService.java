package com.example.mainservice.Service;

import com.example.mainservice.Entity.Book;

import java.util.List;

public interface BookService {
    Book findBookById(int id);

    List<Book> getBooks();

    List<Book> filterBook(String bookName);

    List<Book> getBooksByTypeRelate(String name);

}
