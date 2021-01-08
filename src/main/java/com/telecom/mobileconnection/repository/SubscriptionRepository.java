package com.telecom.mobileconnection.repository;

import com.telecom.mobileconnection.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    public Optional<Subscription> findBySubscriptionId(Integer subscriptionId);

}
