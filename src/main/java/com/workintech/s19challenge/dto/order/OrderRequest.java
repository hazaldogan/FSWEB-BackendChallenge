package com.workintech.s19challenge.dto.order;

import com.workintech.s19challenge.entity.order.Order;
import com.workintech.s19challenge.entity.product.Product;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private List<Product> productList;
    private Order order;
}
