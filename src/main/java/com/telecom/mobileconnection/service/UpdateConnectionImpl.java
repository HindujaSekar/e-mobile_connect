package com.telecom.mobileconnection.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.telecom.mobileconnection.common.SubscriptionStatus;
import com.telecom.mobileconnection.entity.Subscription;
import com.telecom.mobileconnection.repository.SubscriptionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateConnectionImpl {

	private final SubscriptionRepository subscriptionRepository;

	@Scheduled(fixedRate = 10000)
	public void enableConnection() {

		log.info("Connection Enabled :: {}", LocalDateTime.now());

		List<Subscription> subscriptions = subscriptionRepository.findByStatus(SubscriptionStatus.APPROVED.getStatus())
				.stream()
				.peek(subscription -> subscription.setStatus(SubscriptionStatus.CONNECTION_ENABLED.getStatus()))
				.collect(Collectors.toList());
		subscriptionRepository.saveAll(subscriptions);
	}
}
