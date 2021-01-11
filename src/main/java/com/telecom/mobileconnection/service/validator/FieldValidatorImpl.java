package com.telecom.mobileconnection.service.validator;

import org.springframework.stereotype.Component;

import com.telecom.mobileconnection.utils.MobileConnectionContants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FieldValidatorImpl implements FieldValidator {
    String EMAIL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    @Override
    public boolean validEmailId(final String email) {
        Pattern p = Pattern.compile(EMAIL, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        return m.find();
    }

    @Override
    public boolean validPhoneNumber(final String number) {
        return number.matches(MobileConnectionContants.PHONE_NUMBER);
    }

}
