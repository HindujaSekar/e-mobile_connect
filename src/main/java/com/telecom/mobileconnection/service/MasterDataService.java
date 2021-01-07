package com.telecom.mobileconnection.service;

import java.util.List;

import com.telecom.mobileconnection.dto.MobileNumberResponseDTO;
import com.telecom.mobileconnection.dto.PlanResponseDTO;

public interface MasterDataService {
	
	public List<MobileNumberResponseDTO> getAvailableMobileNumbers();
	
	public List<PlanResponseDTO> getListOfPlan();

}
