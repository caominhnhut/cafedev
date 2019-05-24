package com.cafedev.common;

public class MessageConst {

	public static final String ERROR_USER_EXISTS = "This user already exists";
	public static final String ERROR_USER_EMPTY = "User data is null";
	public static final String ERROR_USER_FIRST_NAME = "First name should be alphabet and number";
	public static final String ERROR_USER_LAST_NAME = "Last name should be alphabet and number";
	public static final String ERROR_USER_EMAIL = "The email is incorrect format";
	public static final String ERROR_USER_PHONE = "Phone should be number and greater than or equal 10 digitals";
	public static final String ERROR_USER_ID_EMPTY = "User's id data is null";
	public static final String ERROR_USER_ID_NOT_EXIST = "User's id  data is not exist";
	public static final String ERROR_WRONG_USER_PASSWORD = "Username or password is incorrect";
	public static final String ERROR_WRONG_PASSWORD_LENGTH = "The password should be more than 3 letters";

	public static final String ERROR_ROLE_INVALID = "The roles are not valid";

	public static final String ERROR_FEED_ID_EMPTY = "Feed's id data is null";
	public static final String ERROR_FEED_ID_NOT_EXIST = "Feed's id data is not exist";

	public static final String ERROR_FILE_DIRECTORY = "Could not create the directory where the uploaded file will be stored";
	public static final String ERROR_FILE_NAME_INVALID = "Sorry! Filename contains invalid path sequence %s";
	public static final String ERROR_FILE_NOT_STORED = "Could not store file %s. Please try again";
	public static final String ERROR_FILE_NOTFOUND = "File not found %s";
	public static final String ERROR_FILE_TYPE = "Could not determine file type";
	public static final String FILE_DOWNLOAD = "/rest/examination/downloadFile/";
	public static final String ERROR_DESCRIPTION_EMPTY="Description should be not empty";
	public static final String ERROR_FILEPATH_EMPTY="FilePath data is null";
	public static final String ERROR_RECEIVER_NOT_EXIST = "Receiver's id is null";
	public static final String ERROR_CONTENT_NOT_NULL = "Content is null";
	public static final String ERROR_STATUS_NOT_NULL = "Status is null";
	public static final String ERROR_EXPIRY_DATE="OTP requite enter not over 5 minutes";
	
	public static final String OTP_SUCCESS = "Entered Otp is valid";
	public static final String OTP_FAIL = "The OTP is invalid or expired";
	
}
