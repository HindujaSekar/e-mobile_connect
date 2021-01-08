package com.telecom.mobileconnection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telecom.mobileconnection.entity.Plan;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer>{


}
