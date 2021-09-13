package com.example.springboot_rest.controller;

import com.example.springboot_rest.service.RoleService;
import com.example.springboot_rest.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userService.getUserName(auth.getName()));
        return "index";

    } @GetMapping("/aga")
    public String index1(Model model) {
        model.addAttribute("aga", userService.getAllUsers());
        return "indexforuser";
    }
}


















