package com.workintech.s19challenge.service.user;

import com.workintech.s19challenge.entity.user.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAll();
    Address findbyId(long id);
    Address save(Address address);
    Address delete(long id);
}
