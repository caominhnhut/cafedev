package com.cafedev.batchstep.importuser;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.cafedev.dto.UserRequestDTO;

@Component
public class ProcessorUser implements ItemProcessor<UserRequestDTO, UserRequestDTO>{

	@Override
	public UserRequestDTO process(UserRequestDTO user) throws Exception {
		if(user.getAvatar().isEmpty() || user.getAvatar().length() == 0){
			user.setAvatar("aaa");
		}
		return user;
	}

}
