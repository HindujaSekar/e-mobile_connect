package com.telecom.mobileconnection.controller;

import com.telecom.mobileconnection.dto.SubscriptionResponseDto;
import com.telecom.mobileconnection.dto.UserRequestDto;
import com.telecom.mobileconnection.dto.UserResponseDto;
import com.telecom.mobileconnection.exception.InvalidSubscriptionIdException;
import com.telecom.mobileconnection.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.telecom.mobileconnection.utils.LogConstants.GET_USER_CONTROLLER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * This method in User Controller will take the required User information, validate the information received and,
     * returns the Subscription Id for future tracking.
     *
     * @param userRequestDto Which is the minimum required User Information including name, mailId, address,
     *                       alternateMobileNumber and aadharId
     * @return ResponseDto Which contains the subscriptionId for future status tracking of Connection request
     */
    @PostMapping
    public ResponseEntity<UserResponseDto> subscribeNewConnection(@RequestBody UserRequestDto userRequestDto) {

        log.info(GET_USER_CONTROLLER);
        return new ResponseEntity(userService.subscribeNewConnection(userRequestDto), HttpStatus.CREATED);

    }

    /**
     * This method in User Controller is used to track the Connection Request status.
     *
     * @param subscriptionId Which is the Id of the Connection Request
     * @return ResponseDto Which contains the status details and approver comments If any
     * @throws InvalidSubscriptionIdException will be thrown when the Id is invalid
     */
    @GetMapping("/{subscriptionId}")
    public ResponseEntity<SubscriptionResponseDto> getSubscriptionDetails(@RequestParam("subscriptionId") Integer subscriptionId)
            throws InvalidSubscriptionIdException {

        log.info(GET_USER_CONTROLLER);
        SubscriptionResponseDto subscriptionResponseDto = userService.getSubscriptionStatus(subscriptionId);
        return new ResponseEntity<>(subscriptionResponseDto, HttpStatus.OK);

    }

}
