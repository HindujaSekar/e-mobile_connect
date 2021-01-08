package com.telecom.mobileconnection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.mobileconnection.dto.MobileNumberResponseDto;
import com.telecom.mobileconnection.dto.PlanResponseDto;
import com.telecom.mobileconnection.exception.MobileNumbersNotAvailableException;
import com.telecom.mobileconnection.service.MasterDataService;
import com.telecom.mobileconnection.utils.MobileConnectionContants;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
public class MasterDataController {
	
	@Autowired
	MasterDataService masterDataService;
	
	@GetMapping("numbers")
	public ResponseEntity<List<MobileNumberResponseDto>> getListofMobileNumbers() throws MobileNumbersNotAvailableException
			 {
		log.info(MobileConnectionContants.GET_MASTERDATA_CONTROLLER);
		List<MobileNumberResponseDto> mobileNumberResponseDto = masterDataService.getAvailableMobileNumbers();
		return new ResponseEntity<>(mobileNumberResponseDto, HttpStatus.OK);
	}

	@GetMapping("plans")
	public ResponseEntity<List<PlanResponseDto>> plans(){
		List<PlanResponseDto> planResponseDto = masterDataService.getListOfPlan();
		return new ResponseEntity<>(planResponseDto, HttpStatus.OK);
	}
}
