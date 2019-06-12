package com.cafedev.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cafedev.common.MessageConst;
import com.cafedev.dto.ResponseDTO;
import com.cafedev.dto.UserDTO;
import com.cafedev.dto.UserRequestDTO;
import com.cafedev.model.Role;
import com.cafedev.model.User;
import com.cafedev.service.FileStorageService;
import com.cafedev.service.UserService;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

@RestController
@RequestMapping(value = "/rest/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends RestApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private FileStorageService fileStorageService;

	@RequestMapping(method = GET, value = "/user/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	@RequestMapping(method = GET, value = "/user/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDTO>> loadAll() {
		List<User> users = userService.findAll();
		if (users.size() == 0) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		List<UserDTO> listUserDto = new ArrayList<UserDTO>();
		for (User user : users) {
			UserDTO userDTO = new UserDTO();
			userDTO.showUser(user);
			listUserDto.add(userDTO);
		}
		return new ResponseEntity<List<UserDTO>>(listUserDto, HttpStatus.OK);
	}

	/*
	 * We are not using userService.findByUsername here(we could), so it is good
	 * that we are making sure that the user has role "ROLE_USER" to access this
	 * endpoint.
	 */
	@RequestMapping("/whoami")
	@PreAuthorize("hasRole('USER')")
	public UserDTO user(Principal user) {
		User userRes = this.userService.findByUsername(user.getName());
		UserDTO userDto = new UserDTO();
		userDto.copyFrom(userRes);
		return userDto;
	}

	@RequestMapping(method = RequestMethod.POST, value = "no-auth/user")
	public ResponseEntity<ResponseDTO<Boolean>> create(@RequestBody UserRequestDTO userRequestDto) {
		ResponseDTO<Boolean> response = new ResponseDTO<Boolean>();
		try {
			User user = userRequestDto.toUser();
			ResponseDTO<Boolean> userResult = this.userService.save(user);
			if (userResult.getData() != null) {
				response.setData(userResult.getData());
			}
			response.setErrorMessage(userResult.getErrorMessage());
		} catch (IllegalArgumentException e) {
			response.setErrorMessage(MessageConst.ERROR_ROLE_INVALID);
		}
		return new ResponseEntity<ResponseDTO<Boolean>>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "no-auth/get-role")
	public ResponseEntity<List<Role>> getRole() {
		List<Role> role = userService.getRoles();
		if (role.size() == 0) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Role>>(role, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(method = RequestMethod.PUT, value = "user") public
	 * ResponseEntity<ResponseDTO<UserDTO>> update(@RequestBody UserUpdateRequestDTO
	 * userRequestDto) { ResponseDTO<UserDTO> response = new ResponseDTO<UserDTO>();
	 * User user = new User(); try { user = userRequestDto.toUser();
	 * ResponseDTO<User> userResult = this.userService.update(user,fileName,id);
	 * 
	 * if(userResult.getData() != null){ UserDTO userDTO = new UserDTO();
	 * userDTO.copyFrom(userResult.getData()); response.setData(userDTO); }
	 * response.setErrorMessage(userResult.getErrorMessage()); } catch
	 * (IllegalArgumentException e) {
	 * response.setErrorMessage(MessageConst.ERROR_ROLE_INVALID); } return new
	 * ResponseEntity<ResponseDTO<UserDTO>>(response, HttpStatus.OK); }
	 */

	@RequestMapping(method = RequestMethod.POST, value = "user/update-avatar")
	public ResponseEntity<ResponseDTO<UserDTO>> updateAvatar(@RequestParam("avatar") MultipartFile file,
			@RequestParam("user") String strUser) {
		ResponseDTO<UserDTO> response = new ResponseDTO<UserDTO>();
		String fileName = fileStorageService.storeFile(file);
		UserDTO userDto = getGson().fromJson(strUser, UserDTO.class);
		userDto.setAvatar(fileName);
		ResponseDTO<User> userResult = this.userService.update(userDto.toUser(userDto));
		if (userResult.getData() != null) {
			UserDTO userDTO = new UserDTO();
			userDTO.copyFrom(userResult.getData());
			response.setData(userDTO);
		} else {
			response.setErrorMessage(MessageConst.ERROR_ROLE_INVALID);
		}
		return new ResponseEntity<ResponseDTO<UserDTO>>(response, HttpStatus.OK);
	}
}
