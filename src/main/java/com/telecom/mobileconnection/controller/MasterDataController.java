package com.telecom.mobileconnection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.mobileconnection.dto.MobileNumberResponseDTO;
import com.telecom.mobileconnection.dto.PlanResponseDTO;
import com.telecom.mobileconnection.service.MasterDataService;

@RestController
@RequestMapping("/")
public class MasterDataController {
	
	@Autowired
	MasterDataService masterDataService;
	
	@GetMapping("numbers")
	public ResponseEntity<List<MobileNumberResponseDTO>> register()
			 {
		List<MobileNumberResponseDTO> mobileNumberResponseDTO = masterDataService.getAvailableMobileNumbers();
		return new ResponseEntity<>(mobileNumberResponseDTO, HttpStatus.OK);
	}

	@GetMapping("plans")
	public ResponseEntity<List<PlanResponseDTO>> plans(){
		List<PlanResponseDTO> planResponseDTO = masterDataService.getListOfPlan();
		return new ResponseEntity<>(planResponseDTO, HttpStatus.OK);
	}
}
