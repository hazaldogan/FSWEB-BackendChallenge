package com.workintech.s19challenge.service.user;

import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserCrudServiceImplTest {

    private UserCrudService userCrudService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userCrudService = new UserCrudServiceImpl(userRepository);
    }

    @Test
    void findAll() {
        userCrudService.findAll();
        verify(userRepository).findAll();
    }

    @Test
    void canSave() {
        User user = new User();
        user.setName("Hazal Taştan");
        user.setEmail("hzl@test.com");
        user.setPassword("1234");
        userCrudService.save(user);
        verify(userRepository).save(user);
    }

    @Test
    void canNotSave(){
        User user = new User();
        user.setName("Hazal Taştan");
        user.setEmail("hzl@test.com");
        user.setPassword("1234");
        given(userRepository.findUserByEmail("hzl@test.com")).willReturn(Optional.of(user));
        assertThatThrownBy(()-> userCrudService.save(user))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining("User with given email already exist: ");
        verify(userRepository,never()).save(user);
    }

    @Test
    void delete() {
        User user = new User();
        user.setId(1);
        user.setEmail("hzl@test.com");
        user.setName("Hazal Taştan");
        user.setPassword("1234");

        given(userRepository.findById(Long.valueOf(1))).willReturn(Optional.of(user));

        User deletedUser = userCrudService.delete(user.getId());

        verify(userRepository).delete(user);
        assertEquals("Hazal Taştan",deletedUser.getName());
    }
}