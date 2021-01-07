package com.telecom.mobileconnection.service;

import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.mobileconnection.repository.PlanRepository;
import com.telecom.mobileconnection.repository.SubscriptionRepository;
import com.telecom.mobileconnection.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Autowired
	PlanRepository planRepository;

	Random rand;


	private boolean validateUserName(String userName) {
		String name = ("^[a-zA-Z]*$");
		return userName.matches(name);
	}

	private boolean validEmailId(String email) {
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		java.util.regex.Matcher m = p.matcher(email);
		return (m.find() && m.group().equals(email));
	}

	private boolean validPhoneNumber(Long number) {
		String num = number.toString();
		Pattern p = Pattern.compile("^[0-9]{10}$");
		java.util.regex.Matcher m = p.matcher(num);
		return (m.find() && m.group().equals(num));
	}

	private boolean validPanNo(String panNo) {
		Pattern p = Pattern.compile("^[a-zA-Z1-9]*$", Pattern.CASE_INSENSITIVE);
		java.util.regex.Matcher m = p.matcher(panNo);
		return (m.find() && m.group().equals(panNo));
	}

}
