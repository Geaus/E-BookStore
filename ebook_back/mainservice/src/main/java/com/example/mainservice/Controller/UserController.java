package com.example.mainservice.Controller;
import com.example.mainservice.Entity.*;
import com.example.mainservice.Entity.JSON.consume;
import com.example.mainservice.Repository.BookRepository;
import com.example.mainservice.Repository.OrdersRepository;
import com.example.mainservice.Repository.UserRepository;
import com.example.mainservice.Service.BookService;
import com.example.mainservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    OrdersRepository ordersRepository;

    @RequestMapping("/getUser")
    public User getUser(@RequestParam int uid){

//       User u=userRepository.findUserByIdIs(uid);
//
//       return u;
        return userService.getUser(uid);
    }


    @RequestMapping("/newUser")
    public int newUser(@RequestParam String username,@RequestParam String password,@RequestParam String email){

        return userService.newUser(username,password,email);

    }

    @RequestMapping("/statistic")
    public consume statistic(@RequestParam int uid, @RequestParam String start, @RequestParam String end) throws ParseException {

        return userService.statistic(uid,start,end);
    }


}
