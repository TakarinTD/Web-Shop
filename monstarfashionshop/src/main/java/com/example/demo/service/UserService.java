package com.example.demo.service;

import com.example.demo.entity.*;

public interface UserService {
    User saveUser (User user);

    User findUserById(Long id);

    User findUserByEmail(String email);

    User saveEditUser (User user);

}
