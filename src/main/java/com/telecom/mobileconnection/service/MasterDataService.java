package com.telecom.mobileconnection.service;

import com.telecom.mobileconnection.dto.MobileNumberResponseDto;
import com.telecom.mobileconnection.dto.PlanResponseDto;
import com.telecom.mobileconnection.exception.MobileNumbersNotAvailableException;

import java.util.List;

public interface MasterDataService {

    /**
     * This method in MasterDataService is used to get the available mobile numbers,
     * for the user to choose.
     *
     * @return List of Mobile numbers which are available for the user
     */
    public List<MobileNumberResponseDto> getAvailableMobileNumbers() throws MobileNumbersNotAvailableException;

    /**
     * This method in MasterDataService is used to get the connection plan for user.
     *
     * @return List of Plan Which contains description, price and validity of the plan
     */
    public List<PlanResponseDto> getListOfPlan();

}
