package com.workintech.s19challenge.controller.user;

import com.workintech.s19challenge.dto.user.UserResponse;
import com.workintech.s19challenge.dto.user.UserResponseWithAddress;
import com.workintech.s19challenge.entity.user.Address;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.service.user.UserCrudService;
import com.workintech.s19challenge.service.user.UserService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserCrudService userCrudService;

    @Autowired
    public UserController(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    @GetMapping
    public List<User> findAll(){
        return userCrudService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseWithAddress findById(@Positive @PathVariable long id){
        User user = userCrudService.findById(id);
        List<Address> addressList = new ArrayList<>();
        user.getAddresses().forEach(address -> {
            addressList.add(address);
        });
        return new UserResponseWithAddress(user.getId(), user.getName(), user.getEmail(), user.getAddresses());
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable long id, @RequestBody User user){
        User foundUser = userCrudService.findById(id);
        if(foundUser != null){
            userCrudService.save(user);
        }else{
            throw new GlobalException("User is not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    @DeleteMapping("/{id}")
    public UserResponse delete(@Positive @PathVariable long id){
        User user = userCrudService.findById(id);
        userCrudService.delete(id);
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
