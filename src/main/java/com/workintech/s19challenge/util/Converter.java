package com.workintech.s19challenge.util;

import com.workintech.s19challenge.dto.order.OrderResponse;
import com.workintech.s19challenge.entity.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static List<OrderResponse> orderResponseConverter(List<Order> orderList){
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for(Order order: orderList){
            orderResponseList.add(new OrderResponse(order.getId(),order.getAddress(),order.getProductList()));
        }
        return orderResponseList;
    }

    public static OrderResponse orderResponseConverter(Order order){
        return new OrderResponse(order.getId(),order.getAddress(),order.getProductList());
    }
}
