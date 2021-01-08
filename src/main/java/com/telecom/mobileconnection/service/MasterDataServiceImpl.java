package com.telecom.mobileconnection.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.mobileconnection.dto.MobileNumberResponseDto;
import com.telecom.mobileconnection.dto.PlanResponseDto;
import com.telecom.mobileconnection.entity.MobileNumber;
import com.telecom.mobileconnection.entity.Plan;
import com.telecom.mobileconnection.exception.MobileNumbersNotAvailableException;
import com.telecom.mobileconnection.repository.MobileNumberRepository;
import com.telecom.mobileconnection.repository.PlanRepository;
import com.telecom.mobileconnection.utils.MobileConnectionContants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MasterDataServiceImpl implements MasterDataService {

	@Autowired
	MobileNumberRepository mobileNumberRepository;
	
	@Autowired
	PlanRepository planRepository;

	@Override
	public List<MobileNumberResponseDto> getAvailableMobileNumbers() throws MobileNumbersNotAvailableException{

		log.info(MobileConnectionContants.GET_MASTERDATA_SERVICE);
		Optional<List<MobileNumber>> mobileNumberList = mobileNumberRepository.findByAvailability(MobileConnectionContants.AVAILABLE);
		List<MobileNumberResponseDto> mobileNumberResponseDTOList = new ArrayList<>();
		mobileNumberList.orElseThrow(() -> new MobileNumbersNotAvailableException(MobileConnectionContants.MOBILE_NUMBERS_NOT_FOUND));
		mobileNumberList.get().forEach(mobileNumber -> {
		MobileNumberResponseDto mobileNumberResponseDTO = new MobileNumberResponseDto();
		mobileNumberResponseDTO.setMobileNumberId(mobileNumber.getMobileId());
		mobileNumberResponseDTO.setMobileNumber(mobileNumber.getMobileNumber());
		mobileNumberResponseDTOList.add(mobileNumberResponseDTO);
		
		});
		return mobileNumberResponseDTOList;

	}

	@Override
	public List<PlanResponseDto> getListOfPlan() {
		
		List<Plan> planList = planRepository.findAll();
		List<PlanResponseDto> planResponseDtoList = new ArrayList<>();
		
		
		planList.forEach(plan -> {
			PlanResponseDto planResponseDto = new PlanResponseDto();
			planResponseDto.setPlanId(plan.getPlanId());
			planResponseDto.setPlanDescription(plan.getPlanDescription());
			planResponseDto.setValidity(plan.getValidity());
			planResponseDto.setPrice(plan.getPrice());
			planResponseDtoList.add(planResponseDto);
			});
		return planResponseDtoList;
	}

}
