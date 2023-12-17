package com.example.mainservice.ServiceImpl;

import com.example.mainservice.Dao.BookDao;
import com.example.mainservice.Dao.OrderDao;
import com.example.mainservice.Dao.UserAuthDao;
import com.example.mainservice.Dao.UserDao;
import com.example.mainservice.Entity.*;
import com.example.mainservice.Entity.JSON.consume;
import com.example.mainservice.Entity.JSON.consumeEntry;
import com.example.mainservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    UserAuthDao userAuthDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    BookDao bookDao;

    @Override
    public User getUser(int uid){

        return userDao.findUserByIdIs(uid);
    }

    @Override
    public List<User> getUsers(){

        return userDao.findUsersByUserAuth_UserType(1);
    }

    @Override
    public int newUser(String username,String password,String email){

        if( userAuthDao.findTopByUsername(username) !=null){
            System.out.println("awda");
            return 0;
        }

        UserAuth au=new UserAuth();
        User u=new User();

        u.setName(username);
        u.setEmail(email);
        u.setAvatar("https://s2.loli.net/2023/05/06/bJ3TXu2sMzGlxVS.png");
        u.setIsBlack(0);
        u.setDescription("我是一名普通用户");
        u.setUserAuth(au);

        userDao.save(u);

        au.setUsername(username);
        au.setPassword(password);
        au.setUserType(1);
        au.setIsBlack(0);
        au.setUser(u);

        userAuthDao.save(au);
        return 1;
    }

    @Override
    public consume statistic(@RequestParam int uid, @RequestParam String start, @RequestParam String end) throws ParseException{
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");


        List<Orders> allOrder=new ArrayList<>();

        if(start.equals("")&&end.equals("")){
            allOrder=orderDao.findOrdersByUser_Id(uid);
        }
        else {
            List<Orders> tmp=orderDao.findOrdersByUser_Id(uid);

            for(Orders order:tmp){

                Date time=formatter1.parse(order.getTime());
                Date startDate=formatter2.parse(start);
                Date endDate =formatter2.parse(end);

                if(time.compareTo(startDate)>0 && time.compareTo(endDate)<0){
                    allOrder.add(order);
                }

            }


        }

        consume result=new consume();

        List<Book> allBook= bookDao.findAll();

        List<consumeEntry> tmpEntries=new ArrayList<>();

        for(Book b:allBook){
            consumeEntry tmp=new consumeEntry();
            tmp.setBook(b);
            tmp.setNum(0);
            tmpEntries.add(tmp);
        }

        int book_num=0;
        double price=0;

        for(Orders order:allOrder){

            List<OrderItem> items=order.getOrderItems();

            for(OrderItem item:items){
                int num =item.getNum();
                tmpEntries.get(item.getBook().getId()-1).add(num);
                book_num+=num;
                price+=Double.parseDouble(item.getBook().getPrice())*num ;
            }
        }

        List<consumeEntry> entries=new ArrayList<>();
        for (consumeEntry e:tmpEntries){
            if(e.getNum()!=0){
                entries.add(e);
            }
        }

        result.setEntrys(entries);
        result.setSum_book(book_num);
        price = new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        result.setSum_price(price);
        return result;
    }
}
