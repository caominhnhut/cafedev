package com.cafedev.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafedev.dto.AssignmentDTO;
import com.cafedev.model.Assignment;
import com.cafedev.model.User;
import com.cafedev.service.AssignmentService;

@RestController
@RequestMapping(value="/rest/")
public class AssignmentController {
	
	@Autowired
	private AssignmentService assignmentService; 
	
	@RequestMapping(method=RequestMethod.GET, value= "assignment/find-by-user-id")
	public ResponseEntity<List<AssignmentDTO>> findByUserId(){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		
		List<Assignment> assignments = assignmentService.findByUserId(user.getId());
		if(assignments == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		List<AssignmentDTO> assignmentDTOs = new ArrayList<AssignmentDTO>();
		for(Assignment assignment:assignments) {
			AssignmentDTO assignmentDTO = new AssignmentDTO();
			assignmentDTO.coppyAssignment(assignment);
			assignmentDTOs.add(assignmentDTO);
		}
		return new ResponseEntity<List<AssignmentDTO>>(assignmentDTOs, HttpStatus.OK);
	}
}
