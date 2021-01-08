package com.telecom.mobileconnection.service;

import com.telecom.mobileconnection.common.Availability;
import com.telecom.mobileconnection.common.SubscriptionStatus;
import com.telecom.mobileconnection.dto.SubscriptionResponseDto;
import com.telecom.mobileconnection.dto.UserRequestDto;
import com.telecom.mobileconnection.dto.UserResponseDto;
import com.telecom.mobileconnection.entity.MobileNumber;
import com.telecom.mobileconnection.entity.Subscription;
import com.telecom.mobileconnection.entity.User;
import com.telecom.mobileconnection.exception.DatabaseConnectionException;
import com.telecom.mobileconnection.exception.InvalidCredentialsException;
import com.telecom.mobileconnection.exception.InvalidSubscriptionIdException;
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

import java.time.LocalDate;
import java.util.Optional;

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
		
		log.info(MobileConnectionContants.GET_USER_SERVICE);
		Optional<Subscription> subscriptionDetails = subscriptionRepository.findBySubscriptionId(subscriptionId);
		subscriptionDetails.orElseThrow(() -> new InvalidSubscriptionIdException(MobileConnectionContants.NO_SUBSCRIPTION_ID_FOUND));
			SubscriptionResponseDto subscriptionResponseDto = new SubscriptionResponseDto();
			subscriptionResponseDto.setApproverComments(StringUtils.isEmpty(subscriptionDetails.get().getApproverComments()) 
					? MobileConnectionContants.EMPTY_STRING : subscriptionDetails.get().getApproverComments());
			subscriptionResponseDto.setSubscriptionStatus(subscriptionDetails.get().getStatus());
			subscriptionResponseDto.setStatusCode(HttpStatus.OK.value());
			subscriptionResponseDto.setMessage(MobileConnectionContants.SUBSCRIPTION_MESSAGE);
			return subscriptionResponseDto;
			
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
}
