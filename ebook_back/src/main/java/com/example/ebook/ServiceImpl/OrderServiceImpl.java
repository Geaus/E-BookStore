package com.example.ebook.ServiceImpl;

import com.example.ebook.Dao.BookDao;
import com.example.ebook.Dao.OrderDao;
import com.example.ebook.Dao.UserDao;
import com.example.ebook.Entity.Book;
import com.example.ebook.Entity.OrderItem;
import com.example.ebook.Entity.Orders;
import com.example.ebook.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    BookDao bookDao;

    @Autowired
    UserDao userDao;

    @Override
    public List<Orders> getOrders(int uid){

        return orderDao.findOrdersByUser_Id(uid);
    }
    @Override
    public List<Orders> UserFilterOrderDate(@RequestParam int uid, @RequestParam String start, @RequestParam String end) throws ParseException{

        if(start.equals("")||end.equals("")){
            return orderDao.findOrdersByUser_Id(uid);
        }
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

        List<Orders> orders=orderDao.findOrdersByUser_Id(uid);

        List<Orders> result=new ArrayList<>();

        for(Orders order: orders){

            Date time=formatter1.parse(order.getTime());

            Date startDate=formatter2.parse(start);
            Date endDate =formatter2.parse(end);

            if(time.compareTo(startDate)>0 && time.compareTo(endDate)<0){
                result.add(order);
            }
        }

        return result;
    }
    @Override
    public List<Orders> UserFilterOrderBook(@RequestParam int uid,@RequestParam String bookName){

        if(bookName.equals("")){
            System.out.println("qdw");
        }

        List<Book> books=bookDao.findBooksByNameContaining(bookName);

        List<Orders> allOrder=orderDao.findOrdersByUser_Id(uid);

        List<Orders> result=new ArrayList<>();

        boolean flag=false;
        for(Orders order:allOrder){

            List<OrderItem> items=order.getOrderItems();

            for(OrderItem item:items){

                for(Book b:books){

                    if(item.getBook().getId()==b.getId()){
                        flag=true;

                    }
                }

            }

            if(flag){
                result.add(order);
            }

            flag=false;
        }

        return result;
    }
}
