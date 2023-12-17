package com.example.mainservice.ServiceImpl;

import com.example.mainservice.Dao.*;
import com.example.mainservice.Entity.*;
import com.example.mainservice.Service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartDao cartDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    UserDao userDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderItemDao orderItemDao;

    @Override
    public List<Cart> getCart(int uid){

        return cartDao.findCartsByUser_Id(uid);
    }

    @Override
    public String addCart(int uid,int bookId){

        Book book=bookDao.findBookByIdIs(bookId);
        Cart cart =cartDao.findCartByBook(book);

        if(cart==null){
            cart=new Cart();
            cart.setBook(book);
            cart.setNum(1);
            User u=userDao.findUserByIdIs(uid);
            cart.setUser(u);
        }
        else{
            cart.setNum(cart.getNum()+1);
        }

        cartDao.save(cart);
        return  "购物车添加成功";
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public String makeOrder(int uid) throws JsonProcessingException {

        User u=userDao.findUserByIdIs(uid);

        List<OrderItem> items=new ArrayList<>();

        List<Cart> tmp=cartDao.findCartsByUser_Id(uid);
        List<Cart> carts=new ArrayList<>();
        for(Cart c : tmp){
            if(c.getBook().getExist().equals(1)){
                carts.add(c);
            }
        }
//        if(carts.size()==0){
//            cartDao.deleteCartsByUser(u);
//            return "";
//        }

        Orders newOrder=new Orders();
        newOrder.setUser(u);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(date);

        newOrder.setTime(formattedDate);
        orderDao.save(newOrder);

        for(Cart b : carts){

            Book book=b.getBook();
            OrderItem item = new OrderItem();
            item.setBook(b.getBook());
            item.setOrders(newOrder);
            item.setNum(b.getNum());
            orderItemDao.save(item);
            items.add(item);

            book.setInventory(book.getInventory()-1);
            bookDao.save(book);
        }

        newOrder.setOrderItems(items);
        cartDao.deleteCartsByUser(u);


        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(newOrder);
        return str;
    }
}
