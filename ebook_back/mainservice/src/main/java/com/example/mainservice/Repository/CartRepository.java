package com.example.mainservice.Repository;

import com.example.mainservice.Entity.Book;
import com.example.mainservice.Entity.Cart;
import com.example.mainservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findCartByIdIs(int index);
    List<Cart> findCartsByUser_Id(int index);
    void deleteCartsByUser(User u);

    Cart findCartByBook(Book book);
}