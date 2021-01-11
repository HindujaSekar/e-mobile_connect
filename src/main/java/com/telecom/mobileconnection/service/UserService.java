package com.telecom.mobileconnection.service;

import com.telecom.mobileconnection.dto.ConnectionsResponseDto;
import com.telecom.mobileconnection.dto.SubscriptionResponseDto;
import com.telecom.mobileconnection.dto.UserRequestDto;
import com.telecom.mobileconnection.dto.UserResponseDto;
import com.telecom.mobileconnection.exception.InvalidSubscriptionIdException;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * This method in UserService is used to subscribe a new Connection request by giving,
     * required User details.
     *
     * @param userRequestDto which is the User details like name, mailId, address and alternate mobile number
     * @return ResponseDto Which contains the subscription Id for future tracking of connection request
     */
    UserResponseDto subscribeNewConnection(UserRequestDto userRequestDto);

    /**
     * This method in UserService is used to track the status of connection request by subscription Id.
     *
     * @param requestId Which is the subscription Id
     * @return ResponseDto Which contains the status of Request and approver comments If rejected or referred back
     * @throws InvalidSubscriptionIdException will be thrown in case of invalid Id
     */
    public SubscriptionResponseDto getSubscriptionStatus(Integer requestId) throws InvalidSubscriptionIdException;
    
    /**
     * This method in MasterDataService is used to get the list of In progress connections.
     *
     * @return List of connections Which contains userName, address, aaadharNo, newNumber, alternateNumber, subscriptionId, planId and
     * status of the connection.
     */
    public List<ConnectionsResponseDto> getRequestedSubscriptions(String status);
}

