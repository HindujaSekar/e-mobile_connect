package com.telecom.mobileconnection.service;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.telecom.mobileconnection.common.SubscriptionStatus;
import com.telecom.mobileconnection.dto.SubscriptionResponseDto;
import com.telecom.mobileconnection.entity.Subscription;
import com.telecom.mobileconnection.exception.InvalidSubscriptionIdException;
import com.telecom.mobileconnection.repository.SubscriptionRepository;
import com.telecom.mobileconnection.utils.MobileConnectionContants;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@Mock
	SubscriptionRepository subscriptionRepository;
	
	@Test
	public void testSubscriptionDetailsOk() throws InvalidSubscriptionIdException{
		Subscription subscription = new Subscription();
		subscription.setApproverComments(MobileConnectionContants.EMPTY_STRING);
		subscription.setStatus(SubscriptionStatus.PROGRESS.toString());
		Mockito.when(
				subscriptionRepository.findBySubscriptionId(1))
				.thenReturn(Optional.of(subscription));
		SubscriptionResponseDto subscriptionResponseDto = userServiceImpl.getSubscriptionStatus(1);
		assertNotNull(subscriptionResponseDto);
		assertEquals(MobileConnectionContants.SUBSCRIPTION_MESSAGE, subscriptionResponseDto.getMessage());
		assertEquals(MobileConnectionContants.STATUS_CODE_OK, subscriptionResponseDto.getStatusCode());
	}
	
	@Test
	public void testInvalidSubscriptionId() throws InvalidSubscriptionIdException{
		Mockito.when(
				subscriptionRepository.findBySubscriptionId(210))
				.thenReturn(Optional.empty());
		Assertions.assertThrows(InvalidSubscriptionIdException.class, () -> {
			userServiceImpl.getSubscriptionStatus(210);
		});
	}
}