package com.cafedev.service;

import com.cafedev.model.User;

import java.util.List;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
}
