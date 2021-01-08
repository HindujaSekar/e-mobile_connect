package com.telecom.mobileconnection.controller;

import com.telecom.mobileconnection.dto.MobileNumberResponseDto;
import com.telecom.mobileconnection.dto.PlanResponseDto;
import com.telecom.mobileconnection.exception.MobileNumbersNotAvailableException;
import com.telecom.mobileconnection.service.MasterDataServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class MasterDataControllerTest {

    @InjectMocks
    MasterDataController masterDataController;

    @Mock
    MasterDataServiceImpl masterDataServiceImpl;

    @Test
    public void testGetAvailableMobileNumbers() throws MobileNumbersNotAvailableException {
        List<MobileNumberResponseDto> mobileNumberList = new ArrayList<>();
        mobileNumberList.add(MobileNumberResponseDto.builder()
                .mobileNumber(1234567890L)
                .mobileNumberId(1).build());
        Mockito.when(masterDataServiceImpl.getAvailableMobileNumbers()).thenReturn(mobileNumberList);
        ResponseEntity<List<MobileNumberResponseDto>> mobileNumberResponseDtoList = masterDataController.getListofMobileNumbers();
        assertEquals(HttpStatus.OK, mobileNumberResponseDtoList.getStatusCode());

    }

    @Test
    public void testgetPlanDetailsOk() {
        List<PlanResponseDto> planResponseDtoList = new ArrayList<>();
        planResponseDtoList.add(PlanResponseDto.builder()
                .validity(84)
                .price(599.00)
                .planId(1)
                .planDescription("Unlimited talktime").build());
        Mockito.when(masterDataServiceImpl.getListOfPlan()).thenReturn(planResponseDtoList);
        ResponseEntity<List<PlanResponseDto>> mobileNumberResponseDtoList = masterDataController.plans();
        assertEquals(HttpStatus.OK, mobileNumberResponseDtoList.getStatusCode());

    }

}
