package com.workintech.s19challenge.entity.product;

import com.workintech.s19challenge.entity.user.Address;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="category",schema = "ecommerce")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="code")
    private String code;

    @Column(name="title")
    private String title;

    @Column(name="img")
    private String image;

    @Column(name="rating")
    private double rating;

    @Column(name="gender")
    private char gender;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Product> productList;
    public void addProduct(Product product){
        if(productList == null){
            productList = new ArrayList<>();
        }
        productList.add(product);
    }
}
