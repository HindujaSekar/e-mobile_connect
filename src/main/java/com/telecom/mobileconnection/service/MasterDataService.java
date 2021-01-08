package com.telecom.mobileconnection.service;

import java.util.List;

import com.telecom.mobileconnection.dto.MobileNumberResponseDto;
import com.telecom.mobileconnection.dto.PlanResponseDto;

public interface MasterDataService {
	
	public List<MobileNumberResponseDto> getAvailableMobileNumbers();
	
	public List<PlanResponseDto> getListOfPlan();

}
