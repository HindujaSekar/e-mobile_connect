package com.telecom.mobileconnection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.mobileconnection.entity.MobileNumber;
import com.telecom.mobileconnection.entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer>{

	public List<Plan> findAll();


}
