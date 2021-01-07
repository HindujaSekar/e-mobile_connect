package com.telecom.mobileconnection.repository;

import com.telecom.mobileconnection.entity.MobileNumber;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileNumberRepository extends JpaRepository<MobileNumber, Integer> {
	
	public List<MobileNumber> findByAvailability(String availability);
}
