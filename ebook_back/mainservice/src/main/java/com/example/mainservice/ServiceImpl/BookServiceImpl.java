package com.example.mainservice.ServiceImpl;

import com.example.mainservice.Dao.BookDao;
import com.example.mainservice.Entity.Book;
import com.example.mainservice.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookDao;

    @Override
    public Book findBookById(int id){

        return  bookDao.findBookByIdIs(id);
    }
    @Override
    public List<Book> getBooks(){

        List<Book> result=new ArrayList<>();
        List<Book> allBook=bookDao.findAll();

       for(Book b:allBook){
           if(b.getExist().equals(1)){
               result.add(b);
           }
       }
       return result;
    }

    @Override
    public  List<Book> filterBook(String bookName){

        List<Book> result=new ArrayList<>();
        List<Book> allBook=bookDao.findBooksByNameContaining(bookName);
        for(Book b:allBook){
            if(b.getExist().equals(1)){
                result.add(b);
            }
        }
        return result;

    }

    @Override
    public  List<Book> getBooksByTypeRelate(String name){
        return bookDao.findBooksByTypeRelate(name);
    }

}
