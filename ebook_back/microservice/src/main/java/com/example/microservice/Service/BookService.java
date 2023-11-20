package com.example.microservice.Service;

import com.example.microservice.Dao.BookDao;
import com.example.microservice.Entity.Book;
import com.example.microservice.Entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDao bookDao;

    public List<Result> searchAuthor(String str){
        List<Book> books= bookDao.findBookByNameContain(str);
        List<Result> results=new ArrayList<>();

        for(Book b:books){
            Result tmp=new Result();
            tmp.setName(b.getName());
            tmp.setAuthor(b.getAuthor());
            results.add(tmp);
        }
        return results;
    }
}
