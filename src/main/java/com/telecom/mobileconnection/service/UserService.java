package com.telecom.mobileconnection.service;

import com.telecom.mobileconnection.dto.UserRequestDto;
import com.telecom.mobileconnection.dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponseDto subscribeNewConnection(UserRequestDto userRequestDto);
}

