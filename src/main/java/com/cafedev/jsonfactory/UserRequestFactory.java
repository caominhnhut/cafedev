package com.cafedev.jsonfactory;

import com.cafedev.dto.UserRequestDTO;

public class UserRequestFactory extends JsonRequest{

	@Override
	public String createJson() {
		UserRequestDTO user = new UserRequestDTO();
		user.setAvatar("avata");
		user.setEmail("test01@gmail.com");
		user.setFirstName("test");
		user.setLastName("01");
		user.setPassword("123");
		user.setPhoneNumber("0999999");
		user.setUserName("test-01");
		String json = gson.toJson(user);
		log.info(json);
		return json;
	}

}
