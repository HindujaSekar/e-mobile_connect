package com.telecom.mobileconnection.service.validator;

public interface FieldValidator {

    boolean validEmailId(final String email);

    boolean validPhoneNumber(final String number);
}
