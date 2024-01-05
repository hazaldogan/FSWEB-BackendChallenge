package com.workintech.s19challenge.entity.order;

import com.workintech.s19challenge.entity.product.Product;
import com.workintech.s19challenge.entity.user.Address;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="order",schema = "ecommerce")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="address_id")
    private Address address;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name="order_detail",schema = "ecommerce",joinColumns = @JoinColumn(name="order_id"),
    inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> productList;

    public void addProduct(Product product){
        if(productList == null){
            productList = new ArrayList<>();
        }
        productList.add(product);
    }
}
