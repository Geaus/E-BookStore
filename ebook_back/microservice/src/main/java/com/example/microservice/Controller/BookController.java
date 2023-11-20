package com.example.microservice.Controller;

import com.example.microservice.Entity.Result;
import com.example.microservice.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/searchAuthor")
    public List<Result> searchAuthor (@RequestParam String name){
        return bookService.searchAuthor(name);
    }

}
