package com.app.auth.service;

import com.app.auth.entity.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    List<User> findAll();

    boolean saveUser(User user);

    boolean deleteUserById(Long id);

    List<User> usergtList(Long idMin);
}
