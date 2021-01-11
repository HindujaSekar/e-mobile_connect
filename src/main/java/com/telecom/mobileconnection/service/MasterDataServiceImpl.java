package com.telecom.mobileconnection.service;

import com.telecom.mobileconnection.dto.MobileNumberResponseDto;
import com.telecom.mobileconnection.dto.PlanResponseDto;
import com.telecom.mobileconnection.entity.MobileNumber;
import com.telecom.mobileconnection.entity.Plan;
import com.telecom.mobileconnection.exception.MobileNumbersNotAvailableException;
import com.telecom.mobileconnection.repository.MobileNumberRepository;
import com.telecom.mobileconnection.repository.PlanRepository;
import com.telecom.mobileconnection.utils.MobileConnectionContants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.telecom.mobileconnection.utils.LogConstants.GET_MASTERDATA_SERVICE;

@Service
@Slf4j
public class MasterDataServiceImpl implements MasterDataService {

    @Autowired
    MobileNumberRepository mobileNumberRepository;

    @Autowired
    PlanRepository planRepository;

    @Override
    public List<MobileNumberResponseDto> getAvailableMobileNumbers() throws MobileNumbersNotAvailableException {

        log.info(GET_MASTERDATA_SERVICE);
        Optional<List<MobileNumber>> mobileNumberList = mobileNumberRepository.findByStatus(MobileConnectionContants.AVAILABLE);
        List<MobileNumberResponseDto> mobileNumberResponseDTOList = new ArrayList<>();
        if(mobileNumberList.isPresent()) {
        mobileNumberList.get().forEach(mobileNumber -> mobileNumberResponseDTOList.add(MobileNumberResponseDto.builder()
                .mobileNumberId(mobileNumber.getMobileId())
                .mobileNumber(mobileNumber.getNumber()).build()));
        }else {
        	throw new MobileNumbersNotAvailableException(MobileConnectionContants.MOBILE_NUMBERS_NOT_FOUND);
        }
        return mobileNumberResponseDTOList;

    }

    @Override
    public List<PlanResponseDto> getListOfPlan() {

        log.info(GET_MASTERDATA_SERVICE);
        List<Plan> planList = planRepository.findAll();
        List<PlanResponseDto> planResponseDtoList = new ArrayList<>();
        planList.forEach(plan -> planResponseDtoList.add(PlanResponseDto.builder()
                .planDescription(plan.getPlanDescription())
                .planId(plan.getPlanId())
                .price(plan.getPrice())
                .validity(plan.getValidity())
                .build()));
        return planResponseDtoList;
    }

}
