package com.example.mainservice.Service;

import com.example.mainservice.Entity.Book;
import com.example.mainservice.Entity.JSON.adminConsume;
import com.example.mainservice.Entity.JSON.adminSale;
import com.example.mainservice.Entity.Orders;
import com.example.mainservice.Entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

public interface AdminService {
    List<User> getUsers();
    void deleteBook(@RequestParam int bid);
    void editBook(int bid,String name,
                  String author, String image,
                  String isbn, String inventory,
                  String type, String price,
                  String description
    );

    List<Book> addBook(String name, String author,
                 String image, String isbn,
                 String inventory, String type,
                 String price, String description
    );
    /////////////////
    void banUser( int uid);

    void unbanUser( int uid);
    List<Orders> AdminFilterOrderDate( String start,  String end) throws ParseException;
    List<Orders> AdminFilterOrderBook( String bookName);

    List<adminSale> getHotSell( String start,  String end)throws ParseException;
    List<adminConsume> getConsumption( String start,  String end)throws ParseException;
}
