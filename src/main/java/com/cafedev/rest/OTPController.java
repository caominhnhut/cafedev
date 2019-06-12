package com.cafedev.rest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafedev.common.EmailProvider;
import com.cafedev.common.MessageConst;
import com.cafedev.common.OtpService;
import com.cafedev.config.AppConfigurationProperties;
import com.cafedev.dto.ResponseDTO;
import com.cafedev.model.Mail;
import com.cafedev.model.User;

@Controller
@RequestMapping("/rest/otp/")
public class OTPController extends RestApiController {
	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	public EmailProvider emailProvider;

	@Autowired
	public OtpService otpService;

	@Autowired
	AppConfigurationProperties config;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "send")
	public ResponseEntity<ResponseDTO<Boolean>> sendOTP()
			throws MessagingException, IOException {
		ResponseDTO<Boolean> response = new ResponseDTO<Boolean>();
		User user = getAuthenticatedUser();
		int otp = otpService.generateOTP(user.getEmail());
		Mail mail = new Mail();
		Map model = new HashMap();
		model.put("name", user.getLastName());
		model.put("otp", otp);
		model.put("currentDate", LocalDateTime.now());
		mail.setModel(model);
		try {
			mail.setFrom(config.getEmailFrom());
			mail.setTo(user.getEmail());
			mail.setSubject(config.getEmailTitle());
			emailProvider.sendSimpleMessage(mail);
			response.setData(true);
		} catch (MailException e) {
			response.setErrorMessage(e.getMessage());
			logger.error(e.getMessage());
		}
		return new ResponseEntity<ResponseDTO<Boolean>>(response, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "validate")
	public ResponseEntity<ResponseDTO<Boolean>> checkOTP(
			@RequestParam int otpnum) {
		User user = getAuthenticatedUser();
		ResponseDTO<Boolean> response = new ResponseDTO<Boolean>();
		int serverOtp = otpService.getOtp(user.getEmail());
		if (serverOtp > 0) {
			if (otpnum == serverOtp) {
				response.setData(true);
				otpService.clearOTP(user.getEmail());
			} else {
				response.setErrorMessage(MessageConst.OTP_FAIL);
				logger.error(MessageConst.OTP_FAIL);
			}
		} else {
			response.setErrorMessage(MessageConst.OTP_FAIL);
			logger.error(MessageConst.OTP_FAIL);
		}
		return new ResponseEntity<ResponseDTO<Boolean>>(response, HttpStatus.OK);
	}

}
