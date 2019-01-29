package com.cafedev.batchstep.importuser;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cafedev.dto.UserRequestDTO;
import com.cafedev.model.User;
import com.cafedev.service.UserService;

@Component
public class WriterUser implements ItemWriter<UserRequestDTO>{

	@Autowired
    private UserService userService;
	
	@Override
	public void write(List<? extends UserRequestDTO> userDtos) throws Exception {
		for(UserRequestDTO userDto:userDtos){
			User us = userDto.toUser();
			System.out.println("Importing for "+us.getUsername());
			//us = userService.save(us);
			System.out.println("Finished for "+us.getUsername()+" - "+us.getId());
		}
	}

}
