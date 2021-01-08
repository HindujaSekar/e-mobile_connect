package com.telecom.mobileconnection.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
import com.telecom.mobileconnection.dto.MobileNumberResponseDto;
import com.telecom.mobileconnection.exception.MobileNumbersNotAvailableException;
import com.telecom.mobileconnection.service.MasterDataServiceImpl;

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
		MobileNumberResponseDto mobileNumberResponseDTO = new MobileNumberResponseDto();
		mobileNumberResponseDTO.setMobileNumberId(1);
		mobileNumberResponseDTO.setMobileNumber(1234567890L);
		mobileNumberList.add(mobileNumberResponseDTO);
		Mockito.when(masterDataServiceImpl.getAvailableMobileNumbers()).thenReturn(mobileNumberList);
		ResponseEntity<List<MobileNumberResponseDto>> mobileNumberResponseDtoList = masterDataController.getListofMobileNumbers();
		assertEquals(HttpStatus.OK, mobileNumberResponseDtoList.getStatusCode());

	}

}
