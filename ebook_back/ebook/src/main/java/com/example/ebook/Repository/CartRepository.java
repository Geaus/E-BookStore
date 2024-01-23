package com.example.ebook.Repository;

import com.example.ebook.Entity.Book;
import com.example.ebook.Entity.Cart;
import com.example.ebook.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findCartByIdIs(int index);
    List<Cart> findCartsByUser_Id(int index);
    void deleteCartsByUser(User u);
}