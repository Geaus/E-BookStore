package com.example.ebook.Dao;

import com.example.ebook.Entity.Book;
import com.example.ebook.Entity.OrderItem;
import com.example.ebook.Repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface OrderItemDao {

    void deleteOrderItemsByBook(Book b);

    void save(OrderItem item);
}
