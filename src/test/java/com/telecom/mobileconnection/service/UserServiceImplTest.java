package com.telecom.mobileconnection.service;

import com.telecom.mobileconnection.common.SubscriptionStatus;
import com.telecom.mobileconnection.dto.ConnectionsResponseDto;
import com.telecom.mobileconnection.dto.SubscriptionResponseDto;
import com.telecom.mobileconnection.dto.UserRequestDto;
import com.telecom.mobileconnection.dto.UserResponseDto;
import com.telecom.mobileconnection.entity.MobileNumber;
import com.telecom.mobileconnection.entity.Subscription;
import com.telecom.mobileconnection.entity.User;
import com.telecom.mobileconnection.exception.DatabaseConnectionException;
import com.telecom.mobileconnection.exception.InvalidCredentialsException;
import com.telecom.mobileconnection.exception.InvalidSubscriptionIdException;
import com.telecom.mobileconnection.exception.SubscriptionNotFoundException;
import com.telecom.mobileconnection.repository.MobileNumberRepository;
import com.telecom.mobileconnection.repository.SubscriptionRepository;
import com.telecom.mobileconnection.repository.UserRepository;
import com.telecom.mobileconnection.service.validator.FieldValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.telecom.mobileconnection.common.SubscriptionStatus.PROGRESS;
import static com.telecom.mobileconnection.utils.MobileConnectionContants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private SubscriptionRepository subscriptionRepository;
    @Mock
    private MobileNumberRepository mobileNumberRepository;
    @Mock
    private FieldValidator fieldValidator;

    @InjectMocks
    private UserServiceImpl underTest;

    @Test
    void givenUserRequestDto_whenValidatesAndCreateNewContactSubscription_thenReturnSubscriptionId() {
        when(fieldValidator.validEmailId(anyString())).thenReturn(Boolean.TRUE);
        when(fieldValidator.validPhoneNumber(anyString())).thenReturn(Boolean.TRUE);
        when(userRepository.save(any(User.class))).thenReturn(User.builder().userId(MOBILE_ID).build());
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(Subscription.builder().subscriptionId(MOBILE_ID).mobileId(MOBILE_ID).build());
        when(mobileNumberRepository.findById(any(Integer.class))).thenReturn(Optional.of(MobileNumber.builder().mobileId(MOBILE_ID).build()));
        when(mobileNumberRepository.save(any(MobileNumber.class))).thenReturn(MobileNumber.builder().mobileId(MOBILE_ID).build());
        UserResponseDto responseDto = underTest.subscribeNewConnection(UserRequestDto.builder().userName(USER_NAME)
                .emailId(EMAIL).alternatePhone(PHONE).mobileId(MOBILE_ID).planId(PLANE_ID).build());
        assertEquals(MOBILE_ID, responseDto.getSubscriptionId());
    }

    @Test
    void givenUserRequestDto_whenValidatesEmailFails_thenReturnError() {
        try {
            underTest.subscribeNewConnection(UserRequestDto.builder().userName(USER_NAME)
                    .alternatePhone(PHONE).mobileId(MOBILE_ID).planId(PLANE_ID).build());
        } catch (InvalidCredentialsException invalidCredentialsException) {
            assertEquals(INVALID_EMAIL, invalidCredentialsException.getMessage());
        }
    }

    @Test
    void givenUserRequestDto_whenValidatesPhoneFails_thenReturnError() {
        when(fieldValidator.validEmailId(anyString())).thenReturn(Boolean.TRUE);
        try {
            underTest.subscribeNewConnection(UserRequestDto.builder().userName(USER_NAME)
                    .alternatePhone(PHONE).emailId(EMAIL).mobileId(MOBILE_ID).planId(PLANE_ID).build());
        } catch (InvalidCredentialsException invalidCredentialsException) {
            assertEquals(INVALID_PHONE, invalidCredentialsException.getMessage());
        }
    }

    @Test
    void givenUserRequestDto_whenDbOperationFails_thenReturnError() {
        when(fieldValidator.validEmailId(anyString())).thenReturn(Boolean.TRUE);
        when(fieldValidator.validPhoneNumber(anyString())).thenReturn(Boolean.TRUE);
        when(userRepository.save(any(User.class))).thenReturn(User.builder().userId(MOBILE_ID).build());
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(Subscription.builder().subscriptionId(MOBILE_ID).mobileId(MOBILE_ID).build());
        when(mobileNumberRepository.findById(any(Integer.class))).thenReturn(Optional.of(MobileNumber.builder().build()));
        when(mobileNumberRepository.save(any(MobileNumber.class))).thenReturn(MobileNumber.builder().mobileId(MOBILE_ID).build());
        try {
            underTest.subscribeNewConnection(UserRequestDto.builder().userName(USER_NAME)
                    .alternatePhone(PHONE).emailId(EMAIL).mobileId(MOBILE_ID).planId(PLANE_ID).build());
        } catch (DatabaseConnectionException databaseConnectionException) {
            assertEquals(DB_CONNECTION_ERROR, databaseConnectionException.getMessage());
        }
    }

    @Test
    public void testSubscriptionDetailsOk() throws InvalidSubscriptionIdException {
        Subscription subscription = new Subscription();
        subscription.setApproverComments(EMPTY_STRING);
        subscription.setStatus(PROGRESS.toString());
        when(subscriptionRepository.findBySubscriptionId(1)).thenReturn(Optional.of(subscription));
        SubscriptionResponseDto subscriptionResponseDto = underTest.getSubscriptionStatus(1);
        assertNotNull(subscriptionResponseDto);
        assertEquals(SUBSCRIPTION_MESSAGE, subscriptionResponseDto.getMessage());
        assertEquals(STATUS_CODE_OK, subscriptionResponseDto.getStatusCode());
    }

    @Test
    public void testInvalidSubscriptionId() throws InvalidSubscriptionIdException {
        when(
                subscriptionRepository.findBySubscriptionId(210))
                .thenReturn(Optional.empty());
        assertThrows(InvalidSubscriptionIdException.class, () -> {
            underTest.getSubscriptionStatus(210);
        });
    }
    @Test
    public void testWhenGivenSubscriptionStatusThenGetListOfSubscriptions() throws SubscriptionNotFoundException{
       
        Subscription subscription = Subscription.builder().approverComments(NEW_CONNECTION).subscriptionId(SUBSCRIBE_ID).approverId(APPROVER_ID)
                .mobileId(MOBILE_ID).planId(PLANE_ID).registerDate(REGISTER_DATE).userId(USER_ID).status(SubscriptionStatus.PROGRESS.getStatus()).build();
        List<Subscription> subscriptionList = new ArrayList<>();
        subscriptionList.add(subscription);
       
        User user = User.builder().aadharNo(AADHAR_NUMBER).address(ADDRESS).alternateNumber(PHONE).userName(USER_NAME).build();
       
        when(subscriptionRepository.findByStatus(Mockito.anyString())).thenReturn(subscriptionList);
        when(userRepository.findByUserId(1)).thenReturn(Optional.of(user));
        when(mobileNumberRepository.findByMobileId(1)).thenReturn(Optional.of(MobileNumber.builder().number(MOBILE_NUMBER).build()));
       
        List<ConnectionsResponseDto> response = underTest.getRequestedSubscriptions(SubscriptionStatus.PROGRESS.getStatus());
       
        assertEquals(PLANE_ID, response.get(0).getPlanId());
       
    }
}