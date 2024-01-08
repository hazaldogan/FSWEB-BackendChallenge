package com.workintech.s19challenge.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.service.user.UserCrudService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserCrudService userCrudService;
    @Test
    void remove() throws Exception{
        User user = new User();
        user.setId(1);
        user.setName("Hazal Taştan");
        user.setEmail("hzl@test.com");
        user.setPassword("1234");

        when(userCrudService.findById(1)).thenReturn(user);
        when(userCrudService.delete(user.getId())).thenReturn(user);

        mockMvc.perform(delete("/user/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("hzl@test.com"));
        verify(userCrudService).delete(1);
    }

    public static String jsonToString(Object object){
        try{
            return new ObjectMapper().writeValueAsString(object);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}