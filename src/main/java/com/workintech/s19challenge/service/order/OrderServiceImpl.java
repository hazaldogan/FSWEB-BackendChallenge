package com.workintech.s19challenge.service.order;

import com.workintech.s19challenge.entity.order.Order;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if(orderOptional.isPresent()){
            return orderOptional.get();
        }
        throw new GlobalException("Order is not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order delete(long id) {
        Order order = findById(id);
        if(order != null){
            orderRepository.delete(order);
            return order;
        }
        throw new GlobalException("Order is not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
