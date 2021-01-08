package com.telecom.mobileconnection.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.telecom.mobileconnection.repository.SubscriptionRepository;

@ExtendWith(MockitoExtension.class)
public class UpdateConnectionImplTest {

	@Mock
	private SubscriptionRepository subscriptionRepository;
	
	@InjectMocks
	private UpdateConnectionImpl updateConnection;
}
