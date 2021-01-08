package com.telecom.mobileconnection.controller;

import com.telecom.mobileconnection.dto.UserRequestDto;
import com.telecom.mobileconnection.dto.UserResponseDto;
import com.telecom.mobileconnection.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.telecom.mobileconnection.utils.MobileConnectionContants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController underTest;

    @Test
    void givenUserRequestDto_whenValidatesAndCreateNewContactSubscription_thenReturnSuccessResponseWithSubscriptionId() {
        when(userService.subscribeNewConnection(any(UserRequestDto.class))).thenReturn(UserResponseDto.builder().subscriptionId(SUBSCRIBE_ID).build());
        ResponseEntity<UserResponseDto> response = underTest.subscribeNewConnection(UserRequestDto.builder().userName(USER_NAME)
                .emailId(EMAIL).alternatePhone(PHONE).mobileId(MOBILE_ID).planId(PLANE_ID).build());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());


    }
}