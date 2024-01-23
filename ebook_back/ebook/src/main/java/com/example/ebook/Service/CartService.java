package com.example.ebook.Service;

import com.example.ebook.Entity.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    List<Cart> getCart(int uid);

    String addCart(int uid,int bookId);

    int clearCart(int uid);

}
