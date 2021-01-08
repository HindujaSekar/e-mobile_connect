package com.telecom.mobileconnection.service.validator;

public interface FieldValidator {

    String USER_EMAIL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    String PHONE_NUMBER = "^[6-9]\\d{9}$";

    boolean validEmailId(final String email);

    boolean validPhoneNumber(final String number);


}
