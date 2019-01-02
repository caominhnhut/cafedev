package com.cafedev.batchstep.importuser.v2;

import org.springframework.core.io.Resource;

import com.cafedev.batchstep.ReaderGeneric;
import com.cafedev.dto.UserRequestDTO;

public class ReaderUserV2 extends ReaderGeneric<UserRequestDTO>{

	public ReaderUserV2(Resource resource) {
		super(resource, UserRequestDTO.class);
	}

}
