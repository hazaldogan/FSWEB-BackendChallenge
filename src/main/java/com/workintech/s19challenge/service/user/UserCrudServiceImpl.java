package com.workintech.s19challenge.service.user;

import com.workintech.s19challenge.dto.user.UserResponse;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCrudServiceImpl implements UserCrudService{
    private UserRepository userRepository;

    @Autowired
    public UserCrudServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new GlobalException("User is not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public User save(User user) {
        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());
        if(optionalUser.isPresent()){
            User foundUser = userRepository.findUserByEmail(user.getEmail()).get();
            throw new GlobalException("User with given email already exist: ",HttpStatus.BAD_REQUEST);
        }
        return userRepository.save(user);
    }

    @Override
    public User delete(long id) {
        User user = findById(id);
        if(user != null){
            userRepository.delete(user);
            return user;
        }
        throw new GlobalException("User is not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
