package com.example.mainservice.DaoImpl;

import com.example.mainservice.Dao.OrderItemDao;
import com.example.mainservice.Entity.Book;
import com.example.mainservice.Entity.OrderItem;
import com.example.mainservice.Repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public void deleteOrderItemsByBook(Book b){
        orderItemRepository.deleteOrderItemsByBook(b);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public void save(OrderItem item){
        orderItemRepository.save(item);
//                int a=10/0;
    }
}
