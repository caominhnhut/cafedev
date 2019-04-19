package com.cafedev.service.impl;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafedev.common.MessageConst;
import com.cafedev.dto.ResponseDTO;
import com.cafedev.enums.ENotifyType;
import com.cafedev.model.Notify;
import com.cafedev.model.User;
import com.cafedev.repository.NotifyRepository;
import com.cafedev.repository.UserRepository;
import com.cafedev.service.NotifyService;

@Service
public class NotifyServiceImpl implements NotifyService {

	@Autowired
	private NotifyRepository notifyRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Notify> findByUserId(Long userId) {

		return notifyRepository.findByUserId(userId);
	}

	@Override
	public int count(Long userId) {

		return notifyRepository.count(userId);
	}

	@Override
	public ResponseDTO<Notify> add(Notify notify) {
		ResponseDTO<Notify> response = new ResponseDTO<Notify>();
		boolean isValid = checkValid(notify, response);
		if (isValid) {
			notify.setCreateDate(new Date());
			Notify notifyResult = notifyRepository.add(notify);
			response.setData(notifyResult);
		}
		return response;
	}

	private boolean checkValid(Notify notify, ResponseDTO<Notify> response) {
		List<User> lstUser = userRepository.findUserById(notify.getReceiver().getId());// lay danh sach nguoi nhan
		if (notify.getReceiver().getId() == null) {
			response.setErrorMessage(MessageConst.ERROR_RECEIVER_NOT_EXIST);
			return false;
		} else if (lstUser.size() == 0) {
			response.setErrorMessage(MessageConst.ERROR_USER_ID_NOT_EXIST);
			return false;
		} else if (notify.getContent() == "") {
			response.setErrorMessage(MessageConst.ERROR_CONTENT_NOT_NULL);
			return false;
		}
		return true;
	}

}
