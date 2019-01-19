package com.cafedev.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cafedev.model.Role;
import com.cafedev.model.User;
import com.cafedev.repository.UserRepository;
import com.cafedev.service.UserService;

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
	public User findByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	public User findById(Long id) throws AccessDeniedException {
		// return userRepository.findOne( id );
		return null;
	}

	public List<User> findAll() throws AccessDeniedException {
		// return userRepository.findAll();
		return null;
	}

	@Override
	public User save(User user) {

		List<Role> roles = userRepository.getRoles();
		for (Role role : user.getRoles()) {
			for (Role r : roles) {
				if (r.getName().equals(role.getName())) {
					role.setId(r.getId());
					break;
				}
			}
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		try {
			return userRepository.save(user);
		} catch (Exception e) {
			System.out.println("UserServiceImpl EEEEEEEE: " + e.getMessage());
			return null;
		}
	}
}
