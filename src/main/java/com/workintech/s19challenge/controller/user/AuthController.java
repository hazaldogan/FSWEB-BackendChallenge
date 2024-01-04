package com.workintech.s19challenge.controller.user;

import com.workintech.s19challenge.dto.user.RegisterUser;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.service.user.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public User register(@Validated @RequestBody RegisterUser registerUser){
        return authenticationService.register(registerUser.name(), registerUser.email(),
                registerUser.password());
    }
}
