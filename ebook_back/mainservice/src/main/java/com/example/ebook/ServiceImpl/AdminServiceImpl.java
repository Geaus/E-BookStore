package com.example.ebook.ServiceImpl;

import com.example.ebook.Dao.*;
import com.example.ebook.Entity.*;
import com.example.ebook.Entity.JSON.adminConsume;
import com.example.ebook.Entity.JSON.adminSale;
import com.example.ebook.Service.AdminService;
import com.example.ebook.Service.BookService;
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
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserDao userDao;
    @Autowired
    UserAuthDao userAuthDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    BookService bookService;

    @Override
    public List<User> getUsers(){
        return userDao.findUsersByUserAuth_UserType(1);
    }

    @Override
    public void deleteBook(@RequestParam int bid){

        Book b=bookDao.findBookByIdIs(bid);
        b.setExist(0);
        bookDao.save(b);
    }
    @Override
    public void editBook(int bid,String name,
                         String author, String image,
                         String isbn, String inventory,
                         String type, String price,
                         String description
    ){

        Book b=bookDao.findBookByIdIs(bid);
        b.setName(name);
        b.setAuthor(author);
        b.setImage(image);
        b.setIsbn(isbn);
        b.setInventory(Integer.valueOf(inventory));
        b.setType(type);
        b.setPrice(price);
        b.setDescription(description);

        bookDao.save(b);
    }
    @Override
    public List<Book> addBook(String name, String author,
                              String image, String isbn,
                              String inventory, String type,
                              String price, String description
    ){
        Book tmp=bookDao.findBookByName(name);
        if(tmp!=null && tmp.getExist().equals(0)){

            tmp.setExist(1);
            tmp.setAuthor(author);
            tmp.setImage(image);
            tmp.setIsbn(isbn);
            tmp.setInventory(Integer.valueOf(inventory));
            tmp.setType(type);
            tmp.setPrice(price);
            tmp.setDescription(description);

            bookDao.save(tmp);
            return bookService.getBooks();

        }

        Book b=new Book();
        b.setExist(1);
        b.setName(name);
        b.setAuthor(author);
        b.setImage(image);
        b.setIsbn(isbn);
        b.setInventory(Integer.valueOf(inventory));
        b.setType(type);
        b.setPrice(price);
        b.setDescription(description);
        bookDao.save(b);

        return bookService.getBooks();
    }

    @Override
    public void banUser(@RequestParam int uid){

        User u=userDao.findUserByIdIs(uid);
        u.setIsBlack(1);
        UserAuth a=userAuthDao.findUserAuthById(uid);
        a.setIsBlack(1);

        userDao.save(u);
        userAuthDao.save(a);
    }

    @Override
    public  void unbanUser( int uid){

        User u=userDao.findUserByIdIs(uid);
        u.setIsBlack(0);
        UserAuth a=userAuthDao.findUserAuthById(uid);
        a.setIsBlack(0);

        userDao.save(u);
        userAuthDao.save(a);
    }

    @Override
    public List<Orders> AdminFilterOrderDate( String start,  String end) throws ParseException{

        if(start.equals("")||end.equals("")){
            return orderDao.findAll();
        }
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

        List<Orders> orders=orderDao.findAll();

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
    public List<Orders> AdminFilterOrderBook( String bookName){

        List<Book> books=bookDao.findBooksByNameContaining(bookName);

        List<Orders> allOrder=orderDao.findAll();

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

    @Override
    public List<adminSale> getHotSell( String start, String end)throws ParseException{


        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        List<Orders> orders=AdminFilterOrderDate(start,end);

        List<adminSale> result=new ArrayList<>();
        List<Book> books=bookDao.findAll();


        for(Book b:books){
            adminSale tmp=new adminSale();
            tmp.setBook(b);
            tmp.setNum(0);

           for(Orders order:orders){
               List<OrderItem> items=order.getOrderItems();

               for(OrderItem item:items){

                   if(item.getBook().getId()==b.getId()){
                       tmp.add();
                   }

               }
           }

            result.add(tmp);
        }

        result.sort((a,b)->{

            if(a.getNum()>b.getNum()) return -1;
            else if(a.getNum()==b.getNum()) return 0;
            else return 1;

        });

        for(int i=0;i<result.size();i++){
            result.get(i).setRank(i+1);
        }
        return result;
    }
    @Override
    public List<adminConsume> getConsumption( String start,  String end)throws ParseException{

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

        List<User> users=userDao.findAll();
        List<adminConsume> result=new ArrayList<>();

        for(User user:users){

            adminConsume tmp=new adminConsume();
            tmp.setUser(user);
            tmp.setSpend(0);

            List<Orders> orders=user.getOrders();

            for(Orders order :orders){

                List<OrderItem> items=order.getOrderItems();

                if(start.equals("") && end.equals("")){

                    for(OrderItem item:items){
                        tmp.add(Double.parseDouble(item.getBook().getPrice()));
                    }
                }
                else{

                    Date time=formatter1.parse(order.getTime());
                    Date startDate=formatter2.parse(start);
                    Date endDate =formatter2.parse(end);

                    if(time.compareTo(startDate)>0 && time.compareTo(endDate)<0){

                        for(OrderItem item:items){
                            tmp.add(Double.parseDouble(item.getBook().getPrice()));
                        }
                    }
                }

            }

            tmp.setSpend( new BigDecimal(tmp.getSpend()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            result.add(tmp);

        }

        result.sort((a,b)->{

            if(a.getSpend()>b.getSpend()) return -1;
            else if(a.getSpend()==b.getSpend()) return 0;
            else return 1;

        });

        for(int i=0;i<result.size();i++){
            result.get(i).setRank(i+1);
        }
        return result;
    }
}
