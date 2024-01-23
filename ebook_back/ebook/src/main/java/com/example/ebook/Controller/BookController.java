package com.example.ebook.Controller;
import com.example.ebook.Dao.BookDao;
import com.example.ebook.Entity.Book;
import com.example.ebook.Repository.BookRepository;
import com.example.ebook.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin

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
