package com.telecom.mobileconnection.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.mobileconnection.dto.MobileNumberResponseDto;
import com.telecom.mobileconnection.dto.PlanResponseDto;
import com.telecom.mobileconnection.entity.MobileNumber;
import com.telecom.mobileconnection.entity.Plan;
import com.telecom.mobileconnection.repository.MobileNumberRepository;
import com.telecom.mobileconnection.repository.PlanRepository;

@Service
public class MasterDataServiceImpl implements MasterDataService {

	@Autowired
	MobileNumberRepository mobileNumberRepository;
	
	@Autowired
	PlanRepository planRepository;

	@Override
	public List<MobileNumberResponseDto> getAvailableMobileNumbers() {

		List<MobileNumber> mobileNumberList = mobileNumberRepository.findByAvailability("Available");
		List<MobileNumberResponseDto> mobileNumberResponseDtoList = new ArrayList<>();
		
		mobileNumberList.forEach(mobileNumber -> {
		MobileNumberResponseDto mobileNumberResponseDto = new MobileNumberResponseDto();
		mobileNumberResponseDto.setMobileNumberId(mobileNumber.getMobileId());
		mobileNumberResponseDto.setMobileNumber(mobileNumber.getMobileNumber());
		mobileNumberResponseDtoList.add(mobileNumberResponseDto);
		
		});
		return mobileNumberResponseDtoList;

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
