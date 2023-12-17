package com.example.mainservice.Dao;

import com.example.mainservice.Entity.Orders;
import com.example.mainservice.Entity.User;
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
