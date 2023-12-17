package com.example.mainservice.DaoImpl;

import com.example.mainservice.Dao.OrderDao;
import com.example.mainservice.Entity.Book;
import com.example.mainservice.Entity.OrderItem;
import com.example.mainservice.Entity.Orders;
import com.example.mainservice.Entity.User;
import com.example.mainservice.Repository.BookRepository;
import com.example.mainservice.Repository.OrdersRepository;
import com.example.mainservice.Repository.UserRepository;
import com.example.mainservice.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public void save(Orders orders){
        ordersRepository.save(orders);
        //        int a=10/0;
    }

    @Override
    public  List<Orders> findOrdersByUser(User u){
        return ordersRepository.findOrdersByUser(u);
    }
}
