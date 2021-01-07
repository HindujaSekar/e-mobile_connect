package com.telecom.mobileconnection.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.mobileconnection.dto.MobileNumberResponseDTO;
import com.telecom.mobileconnection.dto.PlanResponseDTO;
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

	@Override
	public List<PlanResponseDTO> getListOfPlan() {
		
		List<Plan> planList = planRepository.findAll();
		List<PlanResponseDTO> planResponseDTOList = new ArrayList<>();
		
		
		planList.forEach(plan -> {
			PlanResponseDTO planResponseDTO = new PlanResponseDTO();
			planResponseDTO.setPlanId(plan.getPlanId());
			planResponseDTO.setPlanDescription(plan.getPlanDescription());
			planResponseDTO.setValidity(plan.getValidity());
			planResponseDTO.setPrice(plan.getPrice());
			planResponseDTOList.add(planResponseDTO);
			});
		return planResponseDTOList;
	}

}
