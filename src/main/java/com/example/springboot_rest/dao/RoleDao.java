package com.example.springboot_rest.dao;



import com.example.springboot_rest.models.Role;

import java.util.List;

public interface RoleDao {
  List<Role> getAllRoles();
  Role findRoles(String role);
  void save(Role role);
}
