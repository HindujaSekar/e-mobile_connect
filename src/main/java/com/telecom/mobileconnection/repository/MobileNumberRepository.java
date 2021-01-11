package com.telecom.mobileconnection.repository;

import com.telecom.mobileconnection.entity.MobileNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MobileNumberRepository extends JpaRepository<MobileNumber, Integer> {

    public Optional<List<MobileNumber>> findByAvailability(String availability);
    
    public Optional<MobileNumber> findByMobileId(Integer mobileId);

}
