package com.workintech.s19challenge.service.user;

import com.workintech.s19challenge.entity.user.Address;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.repository.user.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(optionalAddress.isPresent()){
            return optionalAddress.get();
        }
        throw new GlobalException("Address is not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address delete(long id) {
        Address address = findById(id);
        if(address != null){
            addressRepository.delete(address);
            return address;
        }
        throw new GlobalException("Address is not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
