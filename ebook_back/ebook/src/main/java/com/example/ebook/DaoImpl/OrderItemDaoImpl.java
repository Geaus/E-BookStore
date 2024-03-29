package com.example.ebook.DaoImpl;

import com.example.ebook.Dao.OrderItemDao;
import com.example.ebook.Entity.Book;
import com.example.ebook.Entity.OrderItem;
import com.example.ebook.Repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public void deleteOrderItemsByBook(Book b){
        orderItemRepository.deleteOrderItemsByBook(b);
    }

    @Override
    public void save(OrderItem item){
        orderItemRepository.save(item);
    }
}
