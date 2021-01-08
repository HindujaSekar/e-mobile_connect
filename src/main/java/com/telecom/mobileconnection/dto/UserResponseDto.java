package com.telecom.mobileconnection.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserResponseDto {

    private Integer subscriptionId;
    private Integer statusCode;
}
