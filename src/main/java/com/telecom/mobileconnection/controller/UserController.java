package com.telecom.mobileconnection.controller;

import com.telecom.mobileconnection.dto.UserRequestDto;
import com.telecom.mobileconnection.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.telecom.mobileconnection.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final  UserService userService;

	 @PostMapping
	public ResponseEntity<UserResponseDto> subscribeNewConnection(@RequestBody UserRequestDto userRequestDto){

	 	return new ResponseEntity(userService.subscribeNewConnection(userRequestDto), HttpStatus.CREATED);

	 }

}
