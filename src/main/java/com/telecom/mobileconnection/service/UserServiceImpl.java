package com.telecom.mobileconnection.service;

import com.telecom.mobileconnection.common.Availability;
import com.telecom.mobileconnection.common.SubscriptionStatus;
import com.telecom.mobileconnection.dto.ApproveRequestDTO;
import com.telecom.mobileconnection.dto.ApproveResponseDTO;
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
import com.telecom.mobileconnection.utils.MobileConnectionContants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import static com.telecom.mobileconnection.utils.LogConstants.GET_CONNECTION_SERVICE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.telecom.mobileconnection.utils.LogConstants.GET_USER_SERVICE;
import static com.telecom.mobileconnection.utils.MobileConnectionContants.*;
import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final SubscriptionRepository subscriptionRepository;

    private final MobileNumberRepository mobileNumberRepository;

    private final FieldValidator fieldValidator;


    @Override
    public UserResponseDto subscribeNewConnection(final UserRequestDto userRequestDto) {

        log.info(GET_USER_SERVICE);
        validateEmail(userRequestDto.getEmailId());
        validatePhone(userRequestDto.getAlternatePhone());
        Optional<User> user = Optional.of(userRepository.save(buildUser(userRequestDto)));
        return user.filter(userInfo -> null != userInfo.getUserId()).flatMap((userInfo) -> {
            Optional<Subscription> subscription = Optional.of(subscriptionRepository.save(buildSubscription(userRequestDto, userInfo.getUserId())));
            return subscription.filter(subscriptionInfo -> null != subscriptionInfo.getSubscriptionId()).map(
                    (subscriptionInfo) -> {
                        updateMobileNumberStatus(subscriptionInfo.getMobileId(), Availability.NOT_AVAILABLE.getAvailability());
                        return UserResponseDto.builder().subscriptionId(subscriptionInfo.getSubscriptionId()).statusCode(HttpStatus.CREATED.value()).build();
                    });
        }).orElseThrow(() -> new DatabaseConnectionException(DB_CONNECTION_ERROR));

    }

    @SuppressWarnings("deprecation")
    @Override
    public SubscriptionResponseDto getSubscriptionStatus(Integer subscriptionId) throws InvalidSubscriptionIdException {

        log.info(GET_USER_SERVICE);
        Optional<Subscription> subscriptionDetails = subscriptionRepository.findBySubscriptionId(subscriptionId);
        if(subscriptionDetails.isPresent()) {
        SubscriptionResponseDto subscriptionResponseDto = new SubscriptionResponseDto();
        subscriptionResponseDto.setApproverComments(StringUtils.isEmpty(subscriptionDetails.get().getApproverComments())
                ? MobileConnectionContants.EMPTY_STRING : subscriptionDetails.get().getApproverComments());
        subscriptionResponseDto.setSubscriptionStatus(subscriptionDetails.get().getStatus());
        subscriptionResponseDto.setStatusCode(HttpStatus.OK.value());
        subscriptionResponseDto.setMessage(MobileConnectionContants.SUBSCRIPTION_MESSAGE);
        return subscriptionResponseDto;
        }else {
        	throw new InvalidSubscriptionIdException(MobileConnectionContants.NO_SUBSCRIPTION_ID_FOUND);
        }

    }
    @Override
    public List<ConnectionsResponseDto> getRequestedSubscriptions(String status){
       
        log.info(GET_CONNECTION_SERVICE);
        List<ConnectionsResponseDto> connectionResponse = new ArrayList<>();
        List<Subscription> connectionList = subscriptionRepository.findByStatus(status);
       
        if(connectionList.isEmpty())
            throw new SubscriptionNotFoundException(String.format(MobileConnectionContants.SUBSCRIPTION_STATUS_NOT_FOUND,status));
       
        connectionList.stream().forEach(connections -> {
            Optional<User> user = userRepository.findByUserId(connections.getUserId());
            Optional<MobileNumber> number = mobileNumberRepository.findByMobileId(connections.getMobileId());
            ConnectionsResponseDto response = ConnectionsResponseDto.builder().aadharNo(user.get().getAadharNo())
                    .address(user.get().getAddress()).alternateNumber(user.get().getAlternateNumber())
                    .userName(user.get().getUserName()).status(connections.getStatus())
                    .subscriptionId(connections.getSubscriptionId()).planId(connections.getPlanId())
                    .newNumber(number.get().getMobileNumber()).build();
            connectionResponse.add(response);
        });
        return connectionResponse;
    }
    
    @Override
	public ApproveResponseDTO approveRequestByAdmin(ApproveRequestDTO approveRequestDTO, Integer subscriptionId)
			throws InvalidSubscriptionIdException {
		Optional<Subscription> subscriptionDetails = subscriptionRepository.findBySubscriptionId(subscriptionId);
		if(subscriptionDetails.isPresent()) {
			subscriptionRepository.save(updateSubscriptionStatus(approveRequestDTO, subscriptionDetails.get()));
			return ApproveResponseDTO.builder().message(APPROVER_STATUS_UPDATION).statusCode(HttpStatus.OK.value()).build();
			}else {
				throw new InvalidSubscriptionIdException(SUBSCRITION_ID_EXCEPTION);
			}
		
	}
    
    private void validateEmail(final String email) {
        Optional<Boolean> isValid = Optional.of(fieldValidator.validEmailId(email));
        isValid.filter(TRUE::equals).orElseThrow(() ->
                new InvalidCredentialsException(INVALID_EMAIL));
    }

    private void validatePhone(final String phone) {
        Optional<Boolean> isValid = Optional.of(fieldValidator.validPhoneNumber(phone));
        isValid.filter(TRUE::equals).orElseThrow(() ->
                new InvalidCredentialsException(INVALID_PHONE));
    }

    private User buildUser(final UserRequestDto userRequestDto) {

        return User.builder().userName(userRequestDto.getUserName())
                .emailId(userRequestDto.getEmailId()).alternateNumber(userRequestDto.getAlternatePhone())
                .aadharNo(userRequestDto.getAadharNo()).address(userRequestDto.getAddress()).build();
    }

    private Subscription buildSubscription(final UserRequestDto userRequestDto, final Integer userId) {
        return Subscription.builder().userId(userId).mobileId(userRequestDto.getMobileId())
                .planId(userRequestDto.getPlanId()).status(SubscriptionStatus.PROGRESS.getStatus()).registerDate(LocalDate.now()).build();
    }

    private void updateMobileNumberStatus(final Integer id, final String status) {
        Optional<MobileNumber> mobileNumber = mobileNumberRepository.findById(id);
        mobileNumber.map(phone -> {
            phone.setAvailability(status);
            return mobileNumberRepository.save(phone);
        }).orElseThrow(() -> new DatabaseConnectionException(DB_CONNECTION_ERROR));


    }
    private Subscription updateSubscriptionStatus(final ApproveRequestDTO approveRequestDto, Subscription subscriptionDetails) {
    	return Subscription.builder().subscriptionId(subscriptionDetails.getSubscriptionId()).userId(subscriptionDetails.getUserId()).approverId(subscriptionDetails.getApproverId()).mobileId(subscriptionDetails.getMobileId()).approverComments(approveRequestDto.getApproverComments())
    	.planId(subscriptionDetails.getPlanId()).status(approveRequestDto.getStatus()).registerDate(LocalDate.now()).build();
    	}
}
