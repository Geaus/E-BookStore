package com.example.ebook.ServiceImpl;

import com.example.ebook.Dao.BookDao;
import com.example.ebook.Entity.Book;
import com.example.ebook.Service.BookService;
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

}
