package com.workintech.s19challenge.service.user;

import com.workintech.s19challenge.dto.user.UserResponse;
import com.workintech.s19challenge.entity.user.User;

import java.util.List;

public interface UserCrudService {
    List<User> findAll();
    User findById(long id);
    User save(User user);
    User delete(long id);
}
