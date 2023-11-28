package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User create(@RequestBody User user) {
        User saved = service.create(user);
        saved.setPassword(null);
        return saved;
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public User getCurrent() {
        User user = service.getCurrentUser();
        user.setPassword(null);
        return user;
    }
}
