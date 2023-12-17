package com.example.mainservice.Repository;

import com.example.mainservice.Entity.OrderItem;
import com.example.mainservice.Entity.Orders;
import com.example.mainservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    Orders findOrdersByIdIs(int index);
   List<Orders> findOrdersByUser_Id(int index);

   List<Orders> findOrdersByOrderItemsContains(OrderItem o);

   List<Orders> findOrdersByUser(User u);
}