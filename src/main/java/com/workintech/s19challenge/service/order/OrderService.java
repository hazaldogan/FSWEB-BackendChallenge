package com.workintech.s19challenge.service.order;

import com.workintech.s19challenge.entity.order.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(long id);
    Order save(Order order);
    Order delete(long id);
}
