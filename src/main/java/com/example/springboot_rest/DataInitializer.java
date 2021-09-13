package com.example.springboot_rest;

import com.example.springboot_rest.models.Role;
import com.example.springboot_rest.models.User;
import com.example.springboot_rest.service.RoleService;
import com.example.springboot_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void Init() {
        Role role1 = new Role(1, "ROLE_ADMIN");
        Role role2 = new Role(2, "ROLE_USER");

        roleService.save(role1);
        roleService.save(role2);


        Set<Role> admin = new HashSet<>();
        Set<Role> user = new HashSet<>();

        admin.add(role1);
        admin.add(role2);

        user.add(role2);

        User user1 = new User(12, "adminov", "admin", "admin");
        user1.setPassword(bCryptPasswordEncoder.encode(user1.getPassword()));
        user1.setRoles(admin);
        userService.addUser(user1);

        User user2 = new User(24, "userov", "user", "user");
        user2.setPassword(bCryptPasswordEncoder.encode(user2.getPassword()));
        user2.setRoles(user);
        userService.addUser(user2);
    }
}