package com.example.springboot_rest.service;

import com.example.springboot_rest.dao.UserDao;
import com.example.springboot_rest.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserName(String name) {
        return userDao.getUserName(name);
    }

    @Override
    public User showUserById(int id) {
        return userDao.showUserById(id);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.updateUser(user);
    }

    @Override
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
    }



}