package com.workintech.s19challenge.controller.order;

import com.workintech.s19challenge.dto.order.OrderRequest;
import com.workintech.s19challenge.dto.order.OrderResponse;
import com.workintech.s19challenge.entity.order.Order;
import com.workintech.s19challenge.entity.product.Product;
import com.workintech.s19challenge.entity.user.Address;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.service.order.OrderService;
import com.workintech.s19challenge.service.user.AddressService;
import com.workintech.s19challenge.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    private AddressService addressService;

    @Autowired
    public OrderController(OrderService orderService, AddressService addressService) {
        this.orderService = orderService;
        this.addressService = addressService;
    }

    @GetMapping
    public List<OrderResponse> findAll(){
        List<Order> orderList = orderService.findAll();
        return Converter.orderResponseConverter(orderList);
    }

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable long id){
        return Converter.orderResponseConverter(orderService.findById(id));
    }

    @PostMapping("/{addressId}")
    public OrderResponse save(@PathVariable long addressId, @RequestBody Order order){
        Address address = addressService.findById(addressId);
        if(address != null){
            address.getOrderList().add(order);
            order.setAddress(address);
            List<Product> productList = order.getProductList();
            for(Product product:productList){
                order.addProduct(product);
            }
            orderService.save(order);
        }else{
            throw new GlobalException("Order is not found with id: " + addressId, HttpStatus.NOT_FOUND);
        }
        return Converter.orderResponseConverter(order);
    }

    @PutMapping("/{addressId}")
    public OrderResponse update(@RequestBody Order order, @PathVariable long addressId){
        Address address = addressService.findById(addressId);
        Order foundOrder = null;
        for(Order order1:address.getOrderList()){
            if(order1.getId() == order.getId()){
                foundOrder = order1;
            }
        }
        if(foundOrder == null){
            throw new GlobalException("Order is not found", HttpStatus.BAD_REQUEST);
        }
        int indexOfFound = address.getOrderList().indexOf(foundOrder);
        address.getOrderList().set(indexOfFound,order);
        order.setAddress(address);

        order.setProductList(foundOrder.getProductList());
        orderService.save(order);

        return Converter.orderResponseConverter(order);
    }

    @DeleteMapping("/{id}")
    public Order delete(@PathVariable long id){
        Order order = orderService.findById(id);
        if(order != null){
            orderService.delete(id);
            return order;
        }
        throw new GlobalException("Order is not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
