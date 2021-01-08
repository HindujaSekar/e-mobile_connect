package com.telecom.mobileconnection.controller;

import com.telecom.mobileconnection.dto.MobileNumberResponseDto;
import com.telecom.mobileconnection.dto.PlanResponseDto;
import com.telecom.mobileconnection.exception.MobileNumbersNotAvailableException;
import com.telecom.mobileconnection.service.MasterDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.telecom.mobileconnection.utils.LogConstants.GET_MASTERDATA_CONTROLLER;

@RestController
@RequestMapping("/")
@Slf4j
public class MasterDataController {

    @Autowired
    MasterDataService masterDataService;

    /**
     * This method in MasterDataController is used to get the list of available Mobile Numbers for the user to choose.
     *
     * @return ResponseDto which contains the Available Mobile Number List
     * @throws MobileNumbersNotAvailableException will be thrown when no numbers are available
     */
    @GetMapping("numbers")
    public ResponseEntity<List<MobileNumberResponseDto>> getListofMobileNumbers() throws MobileNumbersNotAvailableException {
        log.info(GET_MASTERDATA_CONTROLLER);
        List<MobileNumberResponseDto> mobileNumberResponseDto = masterDataService.getAvailableMobileNumbers();
        return new ResponseEntity<>(mobileNumberResponseDto, HttpStatus.OK);
    }

    /**
     * This method in MasterDataController is used to get the list of connection plans for the user to choose.
     *
     * @return ResponseDto which contains the description, price, validity of the plan
     */
    @GetMapping("plans")
    public ResponseEntity<List<PlanResponseDto>> plans() {
        log.info(GET_MASTERDATA_CONTROLLER);
        List<PlanResponseDto> planResponseDto = masterDataService.getListOfPlan();
        return new ResponseEntity<>(planResponseDto, HttpStatus.OK);
    }
}
