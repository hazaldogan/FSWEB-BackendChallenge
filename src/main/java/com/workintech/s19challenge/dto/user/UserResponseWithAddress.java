package com.workintech.s19challenge.dto.user;

import com.workintech.s19challenge.entity.user.Address;

import java.util.List;

public record UserResponseWithAddress(long id, String name, String email, List<Address> addressList) {
}
