package com.cafedev.service;

import java.util.List;

import com.cafedev.dto.ResponseDTO;
import com.cafedev.model.Role;
import com.cafedev.model.User;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
    String findAvatar(long id);
    ResponseDTO<Boolean> save(User user);
    ResponseDTO<User> update(User user, String fileName,long id);
    List<Role> getRoles();   
}
