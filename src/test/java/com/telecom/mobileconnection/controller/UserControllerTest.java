package com.telecom.mobileconnection.controller;

import com.telecom.mobileconnection.common.SubscriptionStatus;
import com.telecom.mobileconnection.dto.*;
import com.telecom.mobileconnection.exception.InvalidSubscriptionIdException;
import com.telecom.mobileconnection.service.UserService;
import com.telecom.mobileconnection.utils.MobileConnectionContants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.telecom.mobileconnection.utils.MobileConnectionContants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController underTest;

    @Test
    void givenUserRequestDto_whenValidatesAndCreateNewContactSubscription_thenReturnSuccessResponseWithSubscriptionId() {
        when(userService.subscribeNewConnection(any(UserRequestDto.class))).thenReturn(UserResponseDto.builder().subscriptionId(SUBSCRIBE_ID).build());
        ResponseEntity<UserResponseDto> response = underTest.subscribeNewConnection(UserRequestDto.builder().userName(USER_NAME)
                .emailId(EMAIL).alternatePhone(PHONE).mobileId(MOBILE_ID).planId(PLANE_ID).build());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());


    }

    @Test
    public void testApprovalRequest() throws InvalidSubscriptionIdException {
        ApproveResponseDTO approveResponseDTO = ApproveResponseDTO.builder()
                .message("Approved")
                .statusCode(200)
                .build();
        when(userService.approveRequestByAdmin(any(ApproveRequestDTO.class), anyInt())).thenReturn(approveResponseDTO);
        ResponseEntity<ApproveResponseDTO> response = underTest.approvalRequest(ApproveRequestDTO.builder().approverComments("CLEAR").approverId(1).status("Accepted").build(), SUBSCRIBE_ID);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Approved", response.getBody().getMessage());
    }

    @Test
    public void testGetAvailableMobileNumbers() throws InvalidSubscriptionIdException {
        SubscriptionResponseDto subscriptionResponseDto = new SubscriptionResponseDto();
        subscriptionResponseDto.setApproverComments(MobileConnectionContants.EMPTY_STRING);
        subscriptionResponseDto.setMessage(MobileConnectionContants.SUBSCRIPTION_MESSAGE);
        subscriptionResponseDto.setSubscriptionStatus(SubscriptionStatus.PROGRESS.toString());
        when(userService.getSubscriptionStatus(1)).thenReturn(subscriptionResponseDto);
        ResponseEntity<SubscriptionResponseDto> mobileNumberResponseDtoList = underTest.getSubscriptionDetails(1);
        assertEquals(HttpStatus.OK, mobileNumberResponseDtoList.getStatusCode());

    }
}