package com.telecom.mobileconnection.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.telecom.mobileconnection.dto.MobileNumberResponseDto;
import com.telecom.mobileconnection.entity.MobileNumber;
import com.telecom.mobileconnection.exception.MobileNumbersNotAvailableException;
import com.telecom.mobileconnection.repository.MobileNumberRepository;
import com.telecom.mobileconnection.utils.MobileConnectionContants;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class MasterDataServiceImplTest {
	
	@InjectMocks
	MasterDataServiceImpl masterDataServiceImpl;
	
	@Mock
	MobileNumberRepository mobileNumberRepository;
	
	@Test
	public void testGetAvailableMobileNumbersOk() throws MobileNumbersNotAvailableException{
		List<MobileNumber> mobileNumberList = new ArrayList<>();
		MobileNumber mobileNumber = new MobileNumber();
		mobileNumber.setMobileId(1);
		mobileNumber.setMobileNumber(1234567890L);
		mobileNumber.setAvailability(MobileConnectionContants.AVAILABLE);
		mobileNumberList.add(mobileNumber);
		Mockito.when(
				mobileNumberRepository.findByAvailability(MobileConnectionContants.AVAILABLE))
				.thenReturn(Optional.of(mobileNumberList));
		List<MobileNumberResponseDto> list = masterDataServiceImpl.getAvailableMobileNumbers();
		assertNotNull(list);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testNoMobileNumbersFound() throws MobileNumbersNotAvailableException{
		Mockito.when(
				mobileNumberRepository.findByAvailability("Available"))
				.thenReturn(Optional.empty());
		Assertions.assertThrows(MobileNumbersNotAvailableException.class, () -> {
			masterDataServiceImpl.getAvailableMobileNumbers();
		});

	}
	
}