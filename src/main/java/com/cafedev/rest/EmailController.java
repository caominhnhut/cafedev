package com.cafedev.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafedev.common.MessageConst;
import com.cafedev.common.TimeProvider;
import com.cafedev.dto.ResponseDTO;
import com.cafedev.model.User;

@Controller
@RequestMapping("/rest/")
public class EmailController {

	@Autowired
	public JavaMailSender emailSender;

	@ResponseBody
	@RequestMapping("otp")
	public ResponseEntity<ResponseDTO> sendOTP() {
		ResponseDTO<String> response = new ResponseDTO<String>();
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = (User) authentication.getPrincipal();
		
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(user.getEmail());
		message.setSubject(MessageConst.SUBJECT_EMAIL);
		String otp = generationOTB(4) + TimeProvider.getCurrentTime();
		message.setText(String.format(MessageConst.CONTENT_EMAIL,
				user.getLastName(), otp));
		try {
			this.emailSender.send(message);
			response.setData("Email sent");
		} catch (MailException e) {
			response.setErrorMessage(e.getMessage());
		}
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "otp/validate")
	public ResponseEntity<ResponseDTO<String>> checkOTP(@RequestParam String otb) {
		long timeStartSentEmail = Long.valueOf(otb.substring(4));
		long timeEnterOTP = TimeProvider.getCurrentTime();
		long distanceTime = timeEnterOTP -timeStartSentEmail;
		ResponseDTO<String> response = new ResponseDTO<String>();
		if (TimeProvider.checkOTP(distanceTime, 5, response)) {
			response.setData("Your OTP Invalid");
		} else {
			response.setErrorMessage(response.getErrorMessage());
		}
		return new ResponseEntity<ResponseDTO<String>>(response, HttpStatus.OK);
	}

	public String generationOTB(int length) {
		String numbers = "1234567890";
		Random random = new Random();
		char[] otp = new char[length];

		for (int i = 0; i < length; i++) {
			otp[i] = numbers.charAt(random.nextInt(numbers.length()));
		}
		System.out.println(String.valueOf(otp));
		return String.valueOf(otp);
	}
}
