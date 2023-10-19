package com.example.ebook.Controller;

import com.example.ebook.Dao.*;
import com.example.ebook.Entity.*;
import com.example.ebook.Entity.JSON.adminConsume;
import com.example.ebook.Entity.JSON.adminSale;
import com.example.ebook.Repository.*;
import com.example.ebook.Service.AdminService;
import com.example.ebook.Service.BookService;
import com.example.ebook.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.Order;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    BookDao bookDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    UserAuthDao userAuthDao;
    @Autowired
    UserDao userDao;
    @Autowired
    BookService bookService;

    @Transactional
    @RequestMapping("/deleteBook")
    public List<Book> deleteBook(@RequestParam int bid){

//        Book b=bookDao.findBookByIdIs(bid);
//        b.setExist(0);
//        bookDao.save(b);
        adminService.deleteBook(bid);
        return bookService.getBooks();

    }

    @RequestMapping("/editBook")
    public List<Book> editBook(@RequestParam int bid,
                               @RequestParam String name,
                               @RequestParam String author,
                               @RequestParam String image,
                               @RequestParam String isbn,
                               @RequestParam String inventory,
                               @RequestParam String type,
                               @RequestParam String price,
                               @RequestParam String description
    ){


        adminService.editBook(bid,name,author,image,isbn,inventory,type,price,description);
        return bookService.getBooks();

    }

    @RequestMapping("/addBook")
    public List<Book> addBook(
            @RequestParam String name,
            @RequestParam String author,
            @RequestParam String image,
            @RequestParam String isbn,
            @RequestParam String inventory,
            @RequestParam String type,
            @RequestParam String price,
            @RequestParam String description
    ){


        return adminService.addBook(name,author,image,isbn,inventory,type,price,description);

    }


    @RequestMapping("/getUsers")
    public List<User> getUsers(){

        return adminService.getUsers();
    }

    @Transactional
    @RequestMapping("/banUser")
    public List<User> banUser(@RequestParam int uid){

        adminService.banUser(uid);
        return adminService.getUsers();
    }

    @Transactional
    @RequestMapping("/unbanUser")
    public List<User> unbanUser(@RequestParam int uid){


        adminService.unbanUser(uid);
        return adminService.getUsers();
    }

    @RequestMapping("/AdminFilterOrderDate")
    public List<Orders> AdminFilterOrderDate(@RequestParam String start, @RequestParam String end) throws ParseException{


        return adminService.AdminFilterOrderDate(start,end);
    }
    @RequestMapping("/AdminFilterOrderBook")
    public List<Orders> AdminFilterOrderBook(@RequestParam String bookName){

        return adminService.AdminFilterOrderBook(bookName);
    }

    @RequestMapping("/getSale")
    public List<adminSale> getHotSell(@RequestParam String start, @RequestParam String end)throws ParseException{

        return adminService.getHotSell(start,end);
    }

    @RequestMapping("/getConsumption")
    public List<adminConsume> getConsumption(@RequestParam String start, @RequestParam String end)throws ParseException{

        return adminService.getConsumption(start,end);
    }


}
