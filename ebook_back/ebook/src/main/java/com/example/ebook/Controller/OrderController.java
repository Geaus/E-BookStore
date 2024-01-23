package com.example.ebook.Controller;
import com.example.ebook.Dao.BookDao;
import com.example.ebook.Dao.OrderDao;
import com.example.ebook.Entity.Book;
import com.example.ebook.Entity.OrderItem;
import com.example.ebook.Entity.Orders;
import com.example.ebook.Repository.BookRepository;
import com.example.ebook.Repository.OrdersRepository;
import com.example.ebook.Repository.UserRepository;
import com.example.ebook.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin

public class OrderController {

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderService orderService;

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/getOrders")
    public List<Orders> getOrders(@RequestParam int uid) {

//        return ordersRepository.findOrdersByUser_Id(uid);
        return orderService.getOrders(uid);
    }

    @RequestMapping("UserFilterOrderDate")
    public List<Orders> UserFilterOrderDate(@RequestParam int uid,@RequestParam String start,@RequestParam String end) throws ParseException {

        return orderService.UserFilterOrderDate(uid,start,end);

    }


    @RequestMapping("UserFilterOrderBook")
    public List<Orders> UserFilterOrderBook(@RequestParam int uid,@RequestParam String bookName) throws ParseException {

        return orderService.UserFilterOrderBook(uid,bookName);
    }
}
