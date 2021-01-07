package com.telecom.mobileconnection.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.mobileconnection.dto.MobileNumberResponseDTO;
import com.telecom.mobileconnection.entity.MobileNumber;
import com.telecom.mobileconnection.repository.MobileNumberRepository;

@Service
public class MasterDataServiceImpl implements MasterDataService {

	@Autowired
	MobileNumberRepository mobileNumberRepository;

	@Override
	public List<MobileNumberResponseDTO> getAvailableMobileNumbers() {

		List<MobileNumber> mobileNumberList = mobileNumberRepository.findByAvailability("Available");
		List<MobileNumberResponseDTO> mobileNumberResponseDTOList = new ArrayList<>();
		
		mobileNumberList.forEach(mobileNumber -> {
		MobileNumberResponseDTO mobileNumberResponseDTO = new MobileNumberResponseDTO();
		mobileNumberResponseDTO.setMobileNumberId(mobileNumber.getMobileId());
		mobileNumberResponseDTO.setMobileNumber(mobileNumber.getMobileNumber());
		mobileNumberResponseDTOList.add(mobileNumberResponseDTO);
		
		});
		return mobileNumberResponseDTOList;

	}

}
