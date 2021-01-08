package com.telecom.mobileconnection.repository;

import com.telecom.mobileconnection.entity.MobileNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MobileNumberRepository extends JpaRepository<MobileNumber, Integer> {

    public List<MobileNumber> findByAvailability(String availability);

    @Modifying
    @Query("update MobileNumber m set m.availability= ?1 where m.mobileId = ?2")
    void setMobileInfoById(final String status, final Integer userId);
}
