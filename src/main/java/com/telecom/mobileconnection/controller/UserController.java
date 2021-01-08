package com.telecom.mobileconnection.controller;

import com.telecom.mobileconnection.dto.SubscriptionResponseDto;
import com.telecom.mobileconnection.dto.UserRequestDto;
import com.telecom.mobileconnection.dto.UserResponseDto;
import com.telecom.mobileconnection.exception.InvalidSubscriptionIdException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.telecom.mobileconnection.service.UserService;
import com.telecom.mobileconnection.utils.MobileConnectionContants;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

	private final  UserService userService;

	 @PostMapping
	public ResponseEntity<UserResponseDto> subscribeNewConnection(@RequestBody UserRequestDto userRequestDto){

	 	return new ResponseEntity(userService.subscribeNewConnection(userRequestDto), HttpStatus.CREATED);

	 }
	 
	 @GetMapping("/{subscriptionId}")
		public ResponseEntity<SubscriptionResponseDto> getSubscriptionDetails(@RequestParam("subscriptionId") Integer subscriptionId)
				throws InvalidSubscriptionIdException {

			log.info(MobileConnectionContants.GET_USER_CONTROLLER);
			SubscriptionResponseDto subscriptionResponseDto = userService.getSubscriptionStatus(subscriptionId);
			return new ResponseEntity<>(subscriptionResponseDto, HttpStatus.OK);

		}

}
