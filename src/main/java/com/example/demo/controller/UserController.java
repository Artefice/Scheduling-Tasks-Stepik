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
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public User getCurrent() {
        return service.getCurrentUser();
    }
}
