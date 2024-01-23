package com.example.ebook.DaoImpl;

import com.example.ebook.Dao.CartDao;
import com.example.ebook.Entity.*;
import com.example.ebook.Repository.*;
import com.example.ebook.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    UserRepository userRepository;


///////////////////////
    @Override
    public List<Cart> findCartsByUser_Id(int index){
        return cartRepository.findCartsByUser_Id(index);
    }

    @Override
    public  void save(Cart c){
        cartRepository.save(c);
    }

    @Override
    public  List<Cart> findAll(){
        return cartRepository.findAll();
    }

    @Override
    public  void deleteAll(){
        cartRepository.deleteAll();
    }
    @Override
    public void deleteCartsByUser(User u){
        cartRepository.deleteCartsByUser(u);
    }
}
