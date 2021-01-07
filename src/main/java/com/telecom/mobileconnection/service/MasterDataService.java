package com.telecom.mobileconnection.service;

import java.util.List;

import com.telecom.mobileconnection.dto.MobileNumberResponseDTO;

public interface MasterDataService {
	
	public List<MobileNumberResponseDTO> getAvailableMobileNumbers();

}
