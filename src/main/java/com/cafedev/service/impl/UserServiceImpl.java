package com.cafedev.service.impl;

import com.cafedev.enums.EUserRoleName;
import com.cafedev.model.Role;
import com.cafedev.model.User;
import com.cafedev.repository.UserRepository;
import com.cafedev.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername( String username ) throws UsernameNotFoundException {
        return userRepository.findByUsername( username );
    }

    public User findById( Long id ) throws AccessDeniedException {
        //return userRepository.findOne( id );
    	return null;
    }

    public List<User> findAll() throws AccessDeniedException {
        //return userRepository.findAll();
    	return null;
    }

	@Override
	public Long save(User user) {
		user.getRoles().add(new Role(1l, EUserRoleName.ROLE_USER));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Long userId = userRepository.save(user);
		return userId;
	}
}
