package com.example.springboot_rest.controller;

import com.example.springboot_rest.models.Role;
import com.example.springboot_rest.models.User;
import com.example.springboot_rest.service.RoleService;
import com.example.springboot_rest.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;


    private final UserService userService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", userService.getAllUsers());
        return "adminIndex";
    }


    @PostMapping("/new")
    public String create(@ModelAttribute("user") @Valid User user,
                         @RequestParam(value = "ADMIN", required = false) String ADMIN,
                         @RequestParam(value = "USER", required = false) String USER) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(USER);

        Set<Role> roles = new HashSet<>();
        if(ADMIN != null){
            roles.add(new Role(1,ADMIN));
        }
        if(USER != null){
            roles.add(new Role(2,USER));
        }
        if(ADMIN == null && USER == null ){
            roles.add(new Role(2,USER));
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";
    }



    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("user") @Valid User user,
                         @RequestParam(value = "role", required = false) String[] AllRoles) {

        User user1 = user;
        Set<Role> roles = new HashSet<>();
        for (String role : AllRoles) {
            roles.add(roleService.findRoles(role));
        }
        user1.setRoles(roles);
        userService.updateUser(user1);
        System.out.println(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showUserById(id));
        return "show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
}
