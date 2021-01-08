package com.telecom.mobileconnection.service;

import java.util.List;

import com.telecom.mobileconnection.dto.MobileNumberResponseDto;
import com.telecom.mobileconnection.dto.PlanResponseDto;
import com.telecom.mobileconnection.exception.MobileNumbersNotAvailableException;

public interface MasterDataService {
	
	public List<MobileNumberResponseDto> getAvailableMobileNumbers() throws MobileNumbersNotAvailableException;
	
	public List<PlanResponseDto> getListOfPlan();

}
