package com.example.ebook.Service;

import com.example.ebook.Entity.Cart;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    List<Cart> getCart(int uid);

    String addCart(int uid,int bookId);

    String makeOrder(int uid) throws JsonProcessingException;

}
