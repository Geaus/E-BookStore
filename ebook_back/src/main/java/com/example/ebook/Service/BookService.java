package com.example.ebook.Service;

import com.example.ebook.Entity.Book;

import java.util.List;

public interface BookService {
    Book findBookById(int id);

    List<Book> getBooks();

    List<Book> filterBook(String bookName);
}
