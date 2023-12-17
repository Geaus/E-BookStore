package com.example.mainservice.DaoImpl;

import com.example.mainservice.Dao.UserDao;
import com.example.mainservice.Entity.*;
import com.example.mainservice.Repository.BookRepository;
import com.example.mainservice.Repository.OrdersRepository;
import com.example.mainservice.Repository.UserAuthRepository;
import com.example.mainservice.Repository.UserRepository;
import com.example.mainservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAuthRepository userAuthRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    BookRepository bookRepository;

    //////////////////////
    @Override
    public User findUserByIdIs(int index){
        return userRepository.findUserByIdIs(index);
    }

    @Override
    public List<User> findUsersByUserAuth_UserType(Integer index){
        return userRepository.findUsersByUserAuth_UserType(index);
    }
    @Override
    public  void save(User u){
        userRepository.save(u);
    }

    @Override
    public  List<User> findAll(){
        return userRepository.findAll();
    }
}
