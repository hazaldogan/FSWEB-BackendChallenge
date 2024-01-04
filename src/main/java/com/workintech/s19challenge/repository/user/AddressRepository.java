package com.workintech.s19challenge.repository.user;

import com.workintech.s19challenge.entity.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
