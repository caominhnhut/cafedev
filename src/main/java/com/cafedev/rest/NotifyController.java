package com.cafedev.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafedev.dto.NotifyDTO;
import com.cafedev.dto.ResponseDTO;
import com.cafedev.model.Notify;
import com.cafedev.model.User;
import com.cafedev.service.NotifyService;

@RestController
@RequestMapping(value = "/rest/")
public class NotifyController {

	@Autowired
	private NotifyService notifyService;

	@RequestMapping(method = RequestMethod.GET, value = "notify/find-by-user-id")
	public ResponseEntity<List<NotifyDTO>> findByUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		List<Notify> notifys = notifyService.findByUserId(user.getId());
		if (notifys.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		List<NotifyDTO> notifyDTOs = new ArrayList<NotifyDTO>();
		for (Notify notify : notifys) {
			NotifyDTO notifyDTO = new NotifyDTO();
			//notifyDTO.copyFrom(notify);
			notifyDTOs.add(notifyDTO);
		}
		return new ResponseEntity<List<NotifyDTO>>(notifyDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "notify/count-all-notify-unread")
	public int countAllNotifyUnread() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		int count = notifyService.count(user.getId());
		return count;
	}

	@RequestMapping(method = RequestMethod.POST, value = "notify/insert-data")
	public ResponseEntity<ResponseDTO<NotifyDTO>> add(@RequestBody NotifyDTO notifyDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User sender = (User) authentication.getPrincipal();
		ResponseDTO<NotifyDTO> response = new ResponseDTO<NotifyDTO>();
		Notify notify = notifyDto.toNotify();
		notify.setSender(sender);
		ResponseDTO<Notify> notifyResult = this.notifyService.add(notify);
		if (notifyResult.getData() != null) {
			notifyDto.setId(notifyResult.getData().getId());
			response.setData(notifyDto);
		} else {
			response.setErrorMessage(notifyResult.getErrorMessage());
		}
		return new ResponseEntity<ResponseDTO<NotifyDTO>>(response, HttpStatus.OK);
	}

}
