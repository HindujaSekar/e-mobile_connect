package com.telecom.mobileconnection.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionResponseDto {

    private String subscriptionStatus;
    private String approverComments;
    private Integer statusCode;
    private String message;

}
