package com.cafedev.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cafedev.common.MessageConst;
import com.cafedev.common.RegexMatcher;
import com.cafedev.dto.ResponseDTO;
import com.cafedev.model.User;
import com.cafedev.repository.UserRepository;
import com.cafedev.service.FileStorageService;
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
	
	@Autowired
	private FileStorageService fileStorageService;

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
	public ResponseDTO<User> save(User user) {
		ResponseDTO<User> response = new ResponseDTO<User>();
		boolean isValid = validate(user, response);
		if(isValid){
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			try {
				User userResult =  userRepository.save(user);
				response.setData(userResult);
			} catch (Exception e) {
				Throwable t = e.getCause();
				if(t instanceof ConstraintViolationException){
					response.setErrorMessage(MessageConst.ERROR_USER_EXISTS);
				}
			}
		}
		
		return response;
	}
	
	@Override
	public ResponseDTO<User> update(User user, String fileName, long id) {
		ResponseDTO<User> response = new ResponseDTO<User>();
		boolean isValid = validateUpdate(user, response);
		if (isValid) {
			try {
				String avatar= findAvatar(id);
				System.out.println("1111111"+avatar);
				if (!fileName.isEmpty()) {
					fileStorageService.deleteFile(avatar);
				}
				User userResult = userRepository.update(user);
				response.setData(userResult);
			} catch (Exception e) {
				Throwable t = e.getCause();
				if (t instanceof ConstraintViolationException) {
					response.setErrorMessage(MessageConst.ERROR_USER_EXISTS);
				}
			}
		}

		return response;
	}
	
	private boolean validate(User user, ResponseDTO<User> response){
		if(user == null){
			response.setErrorMessage(MessageConst.ERROR_USER_EMPTY);
			return false;
		}else{
			if(user.getFirstName() == null || !RegexMatcher.isValidName(user.getFirstName())){
				response.setErrorMessage(MessageConst.ERROR_USER_FIRST_NAME);
				return false;
			}
			if(user.getLastName() == null || !RegexMatcher.isValidName(user.getLastName())){
				response.setErrorMessage(MessageConst.ERROR_USER_LAST_NAME);
				return false;
			}
			if(user.getEmail() == null || !RegexMatcher.isValidEmail(user.getEmail())){
				response.setErrorMessage(MessageConst.ERROR_USER_EMAIL);
				return false;
			}
			if(user.getPhoneNumber() == null  || !RegexMatcher.isValidPhone(user.getPhoneNumber())){
				response.setErrorMessage(MessageConst.ERROR_USER_PHONE);
				return false;
			}
			return true;
		}
	}
	
	private boolean validateUpdate(User user, ResponseDTO<User> response){
		if(user.getId() == null){
			response.setErrorMessage(MessageConst.ERROR_USER_EMPTY);
			return false;
		}else{
			if((user.getEmail() != null && !RegexMatcher.isValidEmail(user.getEmail()))){
				response.setErrorMessage(MessageConst.ERROR_USER_EMAIL);
				return false;
			}if(user.getPhoneNumber() != null && !RegexMatcher.isValidPhone(user.getPhoneNumber())){
				response.setErrorMessage(MessageConst.ERROR_USER_PHONE);
				return false;
			}
			return true;
		}
	}

	@Override
	public String findAvatar(long id) {
		return userRepository.findAvatar(id);
	}

}
