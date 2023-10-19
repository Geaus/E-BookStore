package com.example.ebook.Repository;

import com.example.ebook.Entity.OrderItem;
import com.example.ebook.Entity.Orders;
import com.example.ebook.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    Orders findOrdersByIdIs(int index);
   List<Orders> findOrdersByUser_Id(int index);

   List<Orders> findOrdersByOrderItemsContains(OrderItem o);

   List<Orders> findOrdersByUser(User u);
}