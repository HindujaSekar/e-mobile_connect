package com.telecom.mobileconnection.repository;

import com.telecom.mobileconnection.entity.MobileNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileNumberRepository extends JpaRepository<MobileNumber, Integer> {
}
