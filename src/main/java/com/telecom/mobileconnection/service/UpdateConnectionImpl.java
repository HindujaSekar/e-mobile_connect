package com.telecom.mobileconnection.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.telecom.mobileconnection.common.Availability;
import com.telecom.mobileconnection.common.SubscriptionStatus;
import com.telecom.mobileconnection.entity.MobileNumber;
import com.telecom.mobileconnection.entity.Subscription;
import com.telecom.mobileconnection.repository.MobileNumberRepository;
import com.telecom.mobileconnection.repository.SubscriptionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateConnectionImpl {

	private final SubscriptionRepository subscriptionRepository;
	private final MobileNumberRepository mobileNumberRepository;

	@Scheduled(fixedRate = 100000)
	public void enableConnection() {

		log.info("Connection Enabled :: {}", LocalDateTime.now());

		List<Subscription> subscriptions = new ArrayList<>();
		List<MobileNumber> mobileNumbers = new ArrayList<>();

		subscriptionRepository
				.findByStatusIn(
						Arrays.asList(SubscriptionStatus.REJECTED.getStatus(), SubscriptionStatus.APPROVED.getStatus()))
				.stream().forEach(subscription -> {

					if (subscription.getStatus().equals(SubscriptionStatus.APPROVED.getStatus())) {
						subscription.setStatus(SubscriptionStatus.CONNECTION_ENABLED.getStatus());
						subscriptions.add(subscription);
					} else {

						Optional<MobileNumber> optionalMobileNumber = mobileNumberRepository.findByMobileId(subscription.getMobileId());
						optionalMobileNumber.ifPresentOrElse(mobileNumber -> {
							mobileNumber.setAvailability(Availability.AVAILABLE.getAvailability());
							mobileNumbers.add(mobileNumber);
							subscription.setStatus(SubscriptionStatus.CONNECTION_REJECTED.getStatus());
							subscriptions.add(subscription);
						}, null);
					}
				});
		subscriptionRepository.saveAll(subscriptions);
		mobileNumberRepository.saveAll(mobileNumbers);
	}
}