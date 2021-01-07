package com.telecom.mobileconnection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.telecom.mobileconnection.service.UserService;

@RestController
@RequestMapping("/customer")
public class UserController {

	@Autowired
	UserService userService;

}
