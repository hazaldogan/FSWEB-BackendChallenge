package com.workintech.s19challenge.repository.order;

import com.workintech.s19challenge.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
