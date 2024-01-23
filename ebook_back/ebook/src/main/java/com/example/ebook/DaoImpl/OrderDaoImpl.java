package com.example.ebook.DaoImpl;

import com.example.ebook.Dao.OrderDao;
import com.example.ebook.Entity.Book;
import com.example.ebook.Entity.OrderItem;
import com.example.ebook.Entity.Orders;
import com.example.ebook.Entity.User;
import com.example.ebook.Repository.BookRepository;
import com.example.ebook.Repository.OrdersRepository;
import com.example.ebook.Repository.UserRepository;
import com.example.ebook.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;


////////////////////////
    @Override
    public List<Orders> findOrdersByUser_Id(int index){
        return ordersRepository.findOrdersByUser_Id(index);
    }

    @Override
    public List<Orders> findAll(){
        return ordersRepository.findAll();
    }

    @Override
    public void save(Orders orders){
        ordersRepository.save(orders);
    }

    @Override
    public  List<Orders> findOrdersByUser(User u){
        return ordersRepository.findOrdersByUser(u);
    }
}
