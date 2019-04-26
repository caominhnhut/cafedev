package com.cafedev.common;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.cafedev.dto.ResponseDTO;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */
@Component
public class TimeProvider implements Serializable {

	private static final long serialVersionUID = -3301695478208950415L;

	public Date now() {
		return new Date();
	}

	public static String convertDateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		return dateFormat.format(date);
	}

	public static long getCurrentTime() {
		long currentTime = new Date().getTime();
		return currentTime;
	}

	public static Date parseDate(long timeMilli) {
		/*
		 * DateFormat simple = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss"); Date
		 * result = new Date(timeMilli);
		 */
		Date currentDate = new Date(timeMilli);
		return currentDate;
	}

	public static boolean checkOTP(long distanceDate, int timeExpire,
			ResponseDTO response) {
		if ((timeExpire * 60 * 1000) < distanceDate) {
			response.setErrorMessage(MessageConst.ERROR_EXPIRY_DATE);
			return false;
		}
		return true;
	}
}