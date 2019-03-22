package com.warmup.localdate;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MyDate {
	
	public static void main(String[] agrs){
		System.out.println(LocalDate.of(2019, 3, 14));
		System.out.println(LocalDate.now());
		System.out.println(LocalDate.now().minusDays(10));
		System.out.println(LocalDate.now().minusMonths(1));
		System.out.println(Timestamp.valueOf(LocalDateTime.now()));
		System.out.println(Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0))));
	}

}
