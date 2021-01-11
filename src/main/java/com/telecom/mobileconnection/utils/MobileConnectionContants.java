package com.telecom.mobileconnection.utils;

import java.time.LocalDate;

public class MobileConnectionContants {

	private MobileConnectionContants() {
		throw new IllegalStateException("Utility class");
	}
	public static final String MOBILE_NUMBERS_NOT_FOUND = "No Mobile numbers available";
	public static final String AVAILABLE = "Available";
	public static final String INVALID_EMAIL = "Invalid Email please provide valid mail-id";
	public static final String INVALID_PHONE = "Invalid Phone number please provide valid number";
	public static final String DB_CONNECTION_ERROR="Error retrieving data from datasource";
	public static final Integer SUBSCRIBE_ID=1;
	public static final String USER_NAME = "Test User";
	public static final String EMAIL= "abc@gmail.com";
	public static final String PHONE= "9845789087";
	public static final Integer MOBILE_ID= 1;
	public static final Integer PLANE_ID= 1;
	public static final String NO_SUBSCRIPTION_ID_FOUND = "No Such SubscriptionId found";
	public static final String SUBSCRIPTION_MESSAGE = "Subscription details fetched Successfully";
	public static final Integer STATUS_CODE_OK = 200;
	public static final String EMPTY_STRING = "";
	public static final String SUBSCRIPTION_STATUS_NOT_FOUND = "No subscription with %s status has been found";
    public static final String NEW_CONNECTION = "New connection";
    public static final Integer APPROVER_ID = 1;
    public static final LocalDate REGISTER_DATE = LocalDate.now();
    public static final Integer USER_ID = 1;
    public static final String AADHAR_NUMBER = "123456781234";
    public static final String ADDRESS = "Bengaluru";
    public static final Long MOBILE_NUMBER = 9988776655L;
}
