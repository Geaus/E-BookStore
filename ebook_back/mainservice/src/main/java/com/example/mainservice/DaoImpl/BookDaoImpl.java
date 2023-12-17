package com.example.mainservice.DaoImpl;

import com.alibaba.fastjson.JSONArray;
import com.example.mainservice.Dao.BookDao;
import com.example.mainservice.Entity.Book;
import com.example.mainservice.Entity.BookType;
import com.example.mainservice.Entity.Description;
import com.example.mainservice.Redis.RedisOps;
import com.example.mainservice.Repository.BookRepository;
import com.example.mainservice.Repository.MogoDesRepository;
import com.example.mainservice.Repository.Neo4jTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    RedisOps redisOps;
    @Autowired
    MogoDesRepository desRepository;
    @Autowired
    Neo4jTypeRepository neo4jTypeRepository;


    ////////////////////////////////
    @Override
    public Book findBookByIdIs(int index){
        Book book =null;
        Description des = null;

        Object p =redisOps.get("book:"+index);
        if(p==null){
            book=bookRepository.findBookByIdIs(index);
            des = desRepository.findDescriptionBy_id(Integer.toString(index));
            System.out.println(des.getDes());
            book.setDescription(des.getDes());

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

    @Override
    public List<Book> findBooksByTypeRelate(String name){
        System.out.println(name);

//        List<BookType> list0 = neo4jTypeRepository.findBookTypesByTypeLike(name);
        BookType bookType = neo4jTypeRepository.findBookTypeByTypeIs(name);
        HashMap<Integer, Integer> result = new HashMap<>();
        List<Book> resultBook = new ArrayList<>();

//        for (BookType bookType : list0) {
            for (int j = 0; j < bookType.getBookIDs().size(); j++) {
                int id = bookType.getBookIDs().get(j);
                result.put(id, 1);
            }
//        }

//        for (BookType type : list0) {
            String keyName = bookType.getType();
            List<BookType> list1 = neo4jTypeRepository.findTypes_1(keyName);
            List<BookType> list2 = neo4jTypeRepository.findTypes_2(keyName);

            for (BookType bookType1 : list1) {
                for (int j = 0; j < bookType1.getBookIDs().size(); j++) {
                    int id = bookType1.getBookIDs().get(j);
                    result.put(id, 1);
                }
            }

            for (BookType bookType2 : list2) {
                for (int j = 0; j < bookType2.getBookIDs().size(); j++) {
                    int id = bookType2.getBookIDs().get(j);
                    result.put(id, 1);
                }
            }
//        }

        for(int id: result.keySet()){
            resultBook.add(bookRepository.findBookByIdIs(id));
        }

        return resultBook;
    }
}
