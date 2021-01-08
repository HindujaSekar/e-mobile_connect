package com.telecom.mobileconnection.repository;

import com.telecom.mobileconnection.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

    public List<Plan> findAll();


}
