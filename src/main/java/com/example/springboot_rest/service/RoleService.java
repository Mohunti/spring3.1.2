package com.example.springboot_rest.service;

import com.example.springboot_rest.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role findRoles(String role);

    void save(Role role);
}
