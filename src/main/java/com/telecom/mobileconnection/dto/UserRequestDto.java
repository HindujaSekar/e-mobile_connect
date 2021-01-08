package com.telecom.mobileconnection.dto;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;

@Builder
@Getter
public class UserRequestDto {

    @Column(unique = true)
    private String emailId;
    private String userName;
    private String address;
    private String alternatePhone;
    private String aadharNo;
    private Integer mobileId;
    private Integer planId;
}
