package com.example.mainservice.Dao;

import com.example.mainservice.Entity.Book;
import com.example.mainservice.Entity.OrderItem;
import com.example.mainservice.Repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface OrderItemDao {

    void deleteOrderItemsByBook(Book b);

    void save(OrderItem item);
}
