package com.cafedev.common;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */
@Component
public class TimeProvider implements Serializable {

    private static final long serialVersionUID = -3301695478208950415L;

    public Date now() {
        return new Date();
    }
    
    public static String convertDateToString(Date date){
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
    	return dateFormat.format(date);
    }
}