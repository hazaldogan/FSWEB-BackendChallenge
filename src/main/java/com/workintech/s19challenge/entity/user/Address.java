package com.workintech.s19challenge.entity.user;

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
@Table(name="address",schema = "ecommerce")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @NotNull
    @Column(name="title")
    private String title;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="surname")
    private String surname;

    @NotNull
    @Column(name="phone")
    private String phone;

    @NotNull
    @Column(name="city")
    private String city;

    @NotNull
    @Column(name="district")
    private String district;

    @NotNull
    @Column(name="neighbourhood")
    private String neighbourhood;

    @NotNull
    @Column(name="address")
    private String address;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "address")
    private List<Order> orderList;

    public void addOrder(Order order){
        if(orderList != null){
            orderList = new ArrayList<>();
        }
        orderList.add(order);
    }
}
