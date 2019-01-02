package com.cafedev.rest;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafedev.common.DeviceProvider;
import com.cafedev.dto.ResponseMessageDTO;
import com.cafedev.model.User;
import com.cafedev.model.UserTokenState;
import com.cafedev.security.TokenHelper;
import com.cafedev.security.auth.JwtAuthenticationRequest;
import com.cafedev.service.impl.CustomUserDetailsService;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

@RestController
@RequestMapping(value="/auth/", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	TokenHelper tokenHelper;

	@Lazy
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private DeviceProvider deviceProvider;

	private Map<String, String> authenticatedUsers = new HashMap<String, String>();

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response, Device device){

		String token = authenticatedUsers.get(authenticationRequest.getUsername());
		if (token != null && !token.isEmpty()) {
			boolean isTokenExpired = tokenHelper.isTokenExpired(token);
			if (!isTokenExpired) {
				ResponseMessageDTO<String> responseMsg =new ResponseMessageDTO<String>();
				responseMsg.setStatusCode(HttpStatus.UNAUTHORIZED.value());
				responseMsg.setData("This user has already logined");
				return ResponseEntity.ok(responseMsg);
			} else {
				return doLogin(authenticationRequest, device);
			}
		} else {
			return doLogin(authenticationRequest, device);
		}
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public ResponseEntity<?> logout(HttpServletRequest httpRequest){
		String header = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
		String token = header.substring(7);
		String userName = tokenHelper.getUsernameFromToken(token);
		authenticatedUsers.remove(userName);
		return new ResponseEntity(HttpStatus.OK);
	}

	private ResponseEntity<?> doLogin(JwtAuthenticationRequest authenticationRequest, Device device) {
		// Perform the security
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));

		// Inject into security context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// token creation
		User user = (User) authentication.getPrincipal();
		String jws = tokenHelper.generateToken(user.getUsername(), device);
		authenticatedUsers.put(user.getUsername(), jws);
		int expiresIn = tokenHelper.getExpiredIn(device);
		// Return the token
		return ResponseEntity.ok(new UserTokenState(jws, expiresIn));
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request, HttpServletResponse response,
			Principal principal) {

		String authToken = tokenHelper.getToken(request);

		Device device = deviceProvider.getCurrentDevice(request);

		if (authToken != null && principal != null) {
			String refreshedToken = tokenHelper.refreshToken(authToken, device);
			int expiresIn = tokenHelper.getExpiredIn(device);

			return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
		} else {
			UserTokenState userTokenState = new UserTokenState();
			return ResponseEntity.accepted().body(userTokenState);
		}
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}
}