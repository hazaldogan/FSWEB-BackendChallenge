package com.workintech.s19challenge.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
