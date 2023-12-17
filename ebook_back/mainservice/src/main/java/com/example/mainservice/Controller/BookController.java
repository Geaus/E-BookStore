package com.example.mainservice.Controller;
import com.example.mainservice.Dao.BookDao;
import com.example.mainservice.Entity.Book;
import com.example.mainservice.Repository.BookRepository;
import com.example.mainservice.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookService bookService;

    @RequestMapping("/getBook/{bookId}")
    public Book getBookById(@PathVariable("bookId") int bookId) {

//       return  bookRepository.findById(bookId).orElse(null);
        return bookService.findBookById(bookId);

    }

    @QueryMapping
    public Book bookById(@Argument String id){
        int bookid = Integer.parseInt(id);
        System.out.println(bookid);
        return bookService.findBookById(bookid);
    }

    @RequestMapping("/getBook")
    public Book getBook(@RequestParam int bid) {

//       return  bookRepository.findById(bookId).orElse(null);
        return bookService.findBookById(bid);

    }

    @RequestMapping("/getBooks")
    public List<Book> getBooks() {

//        return bookRepository.findAll();
        return bookService.getBooks();
    }

    @RequestMapping("/filterBook")
    public  List<Book> filter(@RequestParam String bookName){
        return bookService.filterBook(bookName);
    }

}
