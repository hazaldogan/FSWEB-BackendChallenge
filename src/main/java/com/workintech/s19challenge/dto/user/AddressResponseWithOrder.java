package com.workintech.s19challenge.dto.user;

import com.workintech.s19challenge.entity.order.Order;

import java.util.List;

public record AddressResponseWithOrder(long id, String title, String name, String surname, String phone,
                                       String city, String district, String neighbourhood, String address,
                                       List<Order> orderList) {
}
