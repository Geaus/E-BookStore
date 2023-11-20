package com.example.ebook.DaoImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.ebook.Dao.BookDao;
import com.example.ebook.Entity.Book;
import com.example.ebook.Redis.RedisOps;
import com.example.ebook.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    RedisOps redisOps;


    ////////////////////////////////
    @Override
    public Book findBookByIdIs(int index){
        Book book =null;
        Object p =redisOps.get("book:"+index);
        if(p==null){
            book=bookRepository.findBookByIdIs(index);
            redisOps.set("book:"+index, JSONArray.toJSON(book));
            System.out.println("Get book:"+index+" in mysql");
        }
        else{
            book = JSONArray.parseObject(p.toString(),Book.class);
            System.out.println("Get book:"+book.getId()+" in redis");

        }
        return book;
//        return bookRepository.findBookByIdIs(index);
    }

    @Override
    public List<Book> findBooksByNameContaining(String name){
        return bookRepository.findBooksByNameContaining(name);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public void save(Book b){
        Object p =redisOps.get("book:"+b.getId());

        if(p!=null){
            if(b.getExist().equals(1)){
                redisOps.set("book:"+b.getId(), JSONArray.toJSON(b));
                System.out.println("Modify book:"+b.getId()+" in redis");
            }
            else{
                redisOps.delete("book:"+b.getId());
                System.out.println("Remove book:"+b.getId()+" in redis");
            }
        }
        else {
            if(b.getExist().equals(1)) {
                redisOps.set("book:" + b.getId(), JSONArray.toJSON(b));
                System.out.println("Add book:" + b.getId() + " in redis");
            }
        }

        System.out.println("Modify or Add book:"+b.getId()+" in mysql");
        bookRepository.save(b);

    }

    @Override
    public List<Book> findAll(){
        List<Book> result = new ArrayList<>();
        long num = bookRepository.count();

        for(long i = 1;i < num;i++){
            Book b =new Book();
            b = findBookByIdIs((int) i);
            result.add(b);
        }
        return result;
    }

    @Override
    public  Book findBookByName(String name){
        return bookRepository.findBookByName(name);
    }
}
