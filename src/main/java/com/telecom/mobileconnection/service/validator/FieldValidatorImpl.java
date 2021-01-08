package com.telecom.mobileconnection.service.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FieldValidatorImpl implements FieldValidator {

    @Override
    public boolean validEmailId(final String email) {
        Pattern p = Pattern.compile(USER_EMAIL, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        return m.find();
    }

    @Override
    public boolean validPhoneNumber(final String number) {
        return number.matches(PHONE_NUMBER);
    }

}