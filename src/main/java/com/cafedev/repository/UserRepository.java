package com.cafedev.repository;

import java.util.List;

import com.cafedev.model.Role;
import com.cafedev.model.User;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

public interface UserRepository {
    User save(User user);
    User findByUsername(String userName);
    List<Role> getRoles();
}

