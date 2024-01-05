package com.workintech.s19challenge.controller.user;

import com.workintech.s19challenge.dto.user.AddressResponseWithOrder;
import com.workintech.s19challenge.dto.user.UserResponseWithAddress;
import com.workintech.s19challenge.entity.order.Order;
import com.workintech.s19challenge.entity.user.Address;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.service.user.AddressService;
import com.workintech.s19challenge.service.user.UserCrudService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;
    private UserCrudService userCrudService;

    @Autowired
    public AddressController(AddressService addressService, UserCrudService userCrudService) {
        this.addressService = addressService;
        this.userCrudService = userCrudService;
    }

    @GetMapping
    public List<Address> findAll(){
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public AddressResponseWithOrder findById(@Positive @PathVariable long id){
        Address address = addressService.findById(id);
        List<Order> orderList = new ArrayList<>();
        address.getOrderList().forEach(order -> {
            orderList.add(order);
        });
        return new AddressResponseWithOrder(address.getId(), address.getTitle(), address.getName(),
                address.getSurname(), address.getPhone(), address.getCity(), address.getDistrict(),
                address.getNeighbourhood(), address.getAddress(), orderList);
    }

    @PostMapping("/{userId}")
    public Address save(@Validated @PathVariable long userId, @RequestBody Address address){
        User user = userCrudService.findById(userId);
        if(user != null){
            user.getAddresses().add(address);
            address.setUser(user);
            addressService.save(address);
        }else{
            throw new GlobalException("User is not found with id: " + userId, HttpStatus.NOT_FOUND);
        }
        return address;
    }

    @PutMapping("/{userId}")
    public Address update(@RequestBody Address address, @PathVariable long userId){
        User user = userCrudService.findById(userId);
        Address foundAddress = null;
        for(Address address1: user.getAddresses()){
            if(address1.getId() == address.getId()){
                foundAddress = address1;
            }
        }
        if(foundAddress == null){
            throw new GlobalException("Address is not found", HttpStatus.BAD_REQUEST);
        }
        int indexOfFound = user.getAddresses().indexOf(foundAddress);
        user.getAddresses().set(indexOfFound,address);
        address.setUser(user);
        addressService.save(address);
        return address;
    }

    @DeleteMapping("/{id}")
    public Address delete(@Positive @PathVariable long id){
        Address address = addressService.findById(id);
        if(address != null){
            addressService.delete(id);
            return address;
        }
        throw new GlobalException("Address is not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
