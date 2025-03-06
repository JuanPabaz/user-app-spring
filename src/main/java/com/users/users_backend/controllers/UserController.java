package com.users.users_backend.controllers;

import com.users.users_backend.services.IUserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


}
