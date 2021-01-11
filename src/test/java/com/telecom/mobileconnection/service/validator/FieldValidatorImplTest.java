package com.telecom.mobileconnection.service.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.telecom.mobileconnection.utils.MobileConnectionContants.EMAIL;
import static com.telecom.mobileconnection.utils.MobileConnectionContants.PHONE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
class FieldValidatorImplTest {

    @InjectMocks
    private FieldValidatorImpl underTest;

    @Test
    void validEmailId() {
        boolean isValid = underTest.validEmailId(EMAIL);
        assertEquals(true, isValid);
    }

    @Test
    void validPhoneNumber() {
        boolean isValid = underTest.validPhoneNumber(PHONE);
        assertEquals(true, isValid);
    }
}