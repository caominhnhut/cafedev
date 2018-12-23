package com.cafedev.service;

import java.util.List;

import com.cafedev.model.User;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
    Long save(User user);
}
