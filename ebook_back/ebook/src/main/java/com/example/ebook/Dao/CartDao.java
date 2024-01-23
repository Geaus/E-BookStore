package com.example.ebook.Dao;

import com.example.ebook.Entity.Cart;
import com.example.ebook.Entity.User;

import java.util.List;

public interface CartDao {

//    List<Cart> getCart(int uid);
//
//    String addCart(int uid,int bookId);
//
//    String clearCart(int uid);

    ////////////////
    List<Cart> findCartsByUser_Id(int index);

    void save(Cart c);

    List<Cart> findAll();

    void deleteAll();

    void deleteCartsByUser(User u);
}
