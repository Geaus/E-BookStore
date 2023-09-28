package com.example.ebook.Dao;

import com.example.ebook.Entity.Book;

import java.util.List;

public interface BookDao {

//   // Book findBookById(int id);
//
//    List<Book> getBooks();
//
//    List<Book> filterBook(String bookName);

    ////////////////////////////////
    Book findBookByIdIs(int index);

    List<Book> findBooksByNameContaining(String name);

    void save(Book b);

    List<Book> findAll();

    Book findBookByName(String name);

}
