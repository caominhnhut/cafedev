package com.cafedev.repository;

import com.cafedev.model.User;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

public interface UserRepository {
    Long save(User user);
    User findByUsername(String userName);
}

