package com.cafedev.service.impl;

import com.cafedev.model.User;
import com.cafedev.repository.UserRepository;
import com.cafedev.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername( String username ) throws UsernameNotFoundException {
        return userRepository.findByUsername( username );
    }

    public User findById( Long id ) throws AccessDeniedException {
        return userRepository.findOne( id );
    }

    public List<User> findAll() throws AccessDeniedException {
        return userRepository.findAll();
    }
}
