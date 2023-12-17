package com.example.mainservice.Controller;

import com.example.mainservice.Dao.BookDao;
import com.example.mainservice.Entity.Book;
import com.example.mainservice.Entity.BookType;
import com.example.mainservice.Repository.Neo4jTypeRepository;
import com.example.mainservice.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Neo4jController {

    @Autowired
    Neo4jTypeRepository neo4jTypeRepository;
    @Autowired
    BookDao bookDao;
    @Autowired
    BookService bookService;

    @RequestMapping("/neo4j")
    public List<Book> init_Neo4j(@RequestParam("type") String type){

        neo4jTypeRepository.deleteAll();

        BookType bookType1 = new BookType("编程");
        BookType bookType2 = new BookType("儿童文学");
        BookType bookType3 = new BookType("魔幻小说");
        BookType bookType4 = new BookType("世界名著");
        BookType bookType5 = new BookType("社会小说");
        BookType bookType6 = new BookType("科幻小说");
//        BookType bookType7 = new BookType("推理小说");

        bookType1.addBookID(1);
        bookType1.addBookID(2);
        bookType1.addBookID(3);
        bookType1.addBookID(5);
        bookType1.addBookID(9);

        bookType2.addBookID(4);

        bookType3.addBookID(6);
        bookType3.addBookID(11);

        bookType4.addBookID(7);
        bookType4.addBookID(12);

        bookType5.addBookID(8);

        bookType6.addBookID(10);


        bookType1.addRelateType(bookType6);
        bookType1.addRelateType(bookType3);

        bookType2.addRelateType(bookType3);
        bookType2.addRelateType(bookType4);

        bookType3.addRelateType(bookType6);
        bookType3.addRelateType(bookType4);

        bookType5.addRelateType(bookType4);


        neo4jTypeRepository.save(bookType1);
        neo4jTypeRepository.save(bookType2);
        neo4jTypeRepository.save(bookType3);
        neo4jTypeRepository.save(bookType4);
        neo4jTypeRepository.save(bookType5);
        neo4jTypeRepository.save(bookType6);
//        neo4jTypeRepository.save(bookType7);

//        return bookDao.findBooksByTypeRelate("社会小说");
        return bookDao.findBooksByTypeRelate(type);
    }

    @RequestMapping("/searchTypeRelate")
    public List<Book> getBooksTypeRelate (@RequestParam("type") String type){
        return bookService.getBooksByTypeRelate(type);
    }
}
