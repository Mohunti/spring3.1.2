package com.example.springboot_rest.service;

import com.example.springboot_rest.dao.RoleDao;
import com.example.springboot_rest.models.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private final RoleDao dao;

    public RoleServiceImpl(RoleDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Role> getAllRoles() {
        return dao.getAllRoles();
    }

    @Override
    public Role findRoles(String role) {
        return dao.findRoles(role);
    }

    @Override
    public void save(Role role) {
        dao.save(role);

    }
}
