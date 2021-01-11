package com.telecom.mobileconnection.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConnectionsResponseDto {

	private String userName;
	private String address;
	private String aadharNo;
	private Long newNumber;
	private String alternateNumber;
	private Integer subscriptionId;
	private Integer planId;
	private String status;

}
