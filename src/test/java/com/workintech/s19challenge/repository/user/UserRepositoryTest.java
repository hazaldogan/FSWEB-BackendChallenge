package com.workintech.s19challenge.repository.user;

import com.workintech.s19challenge.entity.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void createUser(String email){
        User user = new User();
        user.setName("Hazal Taştan");
        user.setEmail(email);
        user.setPassword("1234");
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if(optionalUser.isPresent()){
            User foundUser = userRepository.findUserByEmail(email).get();
            if(foundUser == null){
                userRepository.save(user);
            }
        }
    }

    @BeforeEach
    void setUp() {
        createUser("hzl@test.com");
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @DisplayName("Can find user by email")
    @Test
    void findUserByEmail() {
        Optional<User> optionalUser = userRepository.findUserByEmail("hzl@test.com");
        if(optionalUser.isPresent()){
            User foundUser = userRepository.findUserByEmail("hzl@test.com").get();
            assertNotNull(foundUser);
            assertEquals("Hazal Taştan",foundUser.getName());
        }
    }

    @DisplayName("Cant find user by email")
    @Test
    void findUserByEmailFail(){
        User foundUser = userRepository.findUserByEmail("hazal@test.com").get();
        assertNull(foundUser);
    }
}