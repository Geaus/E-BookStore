package com.example.mainservice.Repository;

import com.example.mainservice.Entity.Book;
import com.example.mainservice.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    OrderItem findOrderItemByIdIs(int index);

    void deleteOrderItemsByBook(Book b);

    List<OrderItem> findOrderItemsByBook(Book b);
}