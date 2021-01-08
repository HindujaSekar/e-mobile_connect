package com.telecom.mobileconnection.service;

import com.telecom.mobileconnection.dto.SubscriptionResponseDto;
import com.telecom.mobileconnection.dto.UserRequestDto;
import com.telecom.mobileconnection.dto.UserResponseDto;
import com.telecom.mobileconnection.exception.InvalidSubscriptionIdException;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponseDto subscribeNewConnection(UserRequestDto userRequestDto);
    
    public SubscriptionResponseDto getSubscriptionStatus(Integer requestId) throws InvalidSubscriptionIdException;
}

