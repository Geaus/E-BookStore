package com.example.ebook.DaoImpl;

import com.example.ebook.Dao.BookDao;
import com.example.ebook.Entity.Book;
import com.example.ebook.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    BookRepository bookRepository;

//    @Override
//    public Book findBookById(int id){
//        return  bookRepository.findById(id).orElse(null);
//    }


    ////////////////////////////////
    @Override
    public Book findBookByIdIs(int index){
        return bookRepository.findBookByIdIs(index);
    }

    @Override
    public List<Book> findBooksByNameContaining(String name){
        return bookRepository.findBooksByNameContaining(name);
    }

    @Override
    public void save(Book b){
        bookRepository.save(b);
    }

    @Override
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @Override
    public  Book findBookByName(String name){
        return bookRepository.findBookByName(name);
    }
}
