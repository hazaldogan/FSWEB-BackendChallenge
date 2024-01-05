package com.workintech.s19challenge.entity.product;

import com.workintech.s19challenge.entity.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="product",schema="ecommerce")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotNull
    @Column(name="name")
    private String name;

    @Column(name="decription")
    private String description;

    @NotNull
    @Column(name="price")
    private double price;

    @NotNull
    @Column(name="stock")
    private int stock;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="category_id")
    private Category category;

    @Column(name="rating")
    private double rating;

    @Column(name="sell_count")
    private long sellCount;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name="order_detail",schema="ecommerce",joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="order_id"))
    private List<Order> orderList;

    public void addOrder(Order order){
        if(orderList == null){
            orderList = new ArrayList<>();
        }
        orderList.add(order);
    }

}
