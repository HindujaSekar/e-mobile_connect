package com.telecom.mobileconnection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.mobileconnection.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
