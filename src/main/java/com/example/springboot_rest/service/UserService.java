package com.example.springboot_rest.service;



import com.example.springboot_rest.models.User;

import java.util.List;

public interface UserService  {
    List<User> getAllUsers();

    User showUserById(int id);

    void addUser(User user);

    void updateUser(User user);

    void removeUser(int id);

    void save(User user);
    public User getUserName(String name);
}