package com.example.mainservice.Service;

import com.example.mainservice.Entity.Orders;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

public interface OrderService {
    List<Orders> getOrders(int uid);

    List<Orders> UserFilterOrderDate(@RequestParam int uid, @RequestParam String start, @RequestParam String end) throws ParseException;
    List<Orders> UserFilterOrderBook(@RequestParam int uid,@RequestParam String bookName);
}
