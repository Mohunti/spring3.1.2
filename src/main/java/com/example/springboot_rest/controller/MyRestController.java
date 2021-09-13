package com.example.springboot_rest.controller;

import com.example.springboot_rest.exception_handling.NoSuchUserException;
import com.example.springboot_rest.models.User;
import com.example.springboot_rest.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api")
public class MyRestController {

    private final UserService userService;

    public MyRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public List<User> list() {
        return userService.getAllUsers();
    }

    @GetMapping("/allUsers/{id}")
    public User getUser(@PathVariable int id) {
        User user = userService.showUserById(id);
        if (user == null) {
            throw new NoSuchUserException("There is no user with ID = " + id + " in Database");
        }
        return user;
    }


}
