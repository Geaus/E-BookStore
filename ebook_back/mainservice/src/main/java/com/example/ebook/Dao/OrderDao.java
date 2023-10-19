package com.example.ebook.Dao;

import com.example.ebook.Entity.Orders;
import com.example.ebook.Entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

public interface OrderDao {

//
//    List<Orders> getOrders(int uid);
//    List<Orders> UserFilterOrderDate(@RequestParam int uid, @RequestParam String start, @RequestParam String end) throws ParseException;
//    List<Orders> UserFilterOrderBook(@RequestParam int uid,@RequestParam String bookName);

    ////////////////////////
    List<Orders> findOrdersByUser_Id(int index);

    List<Orders> findAll();

    void save(Orders orders);

    List<Orders> findOrdersByUser(User u);

}
