package com.example.ebook.ServiceImpl;

import com.example.ebook.Dao.*;
import com.example.ebook.Entity.*;
import com.example.ebook.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        Cart cart=new Cart();
        cart.setBook(book);
        User u=userDao.findUserByIdIs(uid);
        cart.setUser(u);
        cartDao.save(cart);
        return  "购物车添加成功";
    }

    @Transactional
    @Override
    public int clearCart(int uid){

        User u=userDao.findUserByIdIs(uid);

        List<OrderItem> items=new ArrayList<>();
        List<Book> books=new ArrayList<>();

        List<Cart> tmpl=cartDao.findCartsByUser_Id(uid);

        System.out.println("1111");

        List<Cart> carts=new ArrayList<>();
        for(Cart c : tmpl){
            if(c.getBook().getExist().equals(1)){
                carts.add(c);
            }
        }

        if(carts.size()==0){
            cartDao.deleteCartsByUser(u);
            return 0;
        }


        Orders newOrder=new Orders();
        newOrder.setUser(u);
        System.out.println("1111");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(date);
        newOrder.setTime(formattedDate);
        orderDao.save(newOrder);



        for(Cart b : carts){
            books.add(b.getBook());
        }

        for (Book b : books) {

            OrderItem item = new OrderItem();
            item.setBook(b);
            item.setOrders(newOrder);
            orderItemDao.save(item);

            items.add(item);
        }

        for (Book b : books) {

            int tmp=b.getInventory();
            b.setInventory(tmp-1);
            bookDao.save(b);

        }

        newOrder.setOrderItems(items);
        cartDao.deleteCartsByUser(u);

        return  1;
    }

}
