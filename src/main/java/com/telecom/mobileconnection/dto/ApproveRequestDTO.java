package com.telecom.mobileconnection.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApproveRequestDTO {

	private String status;
	private String approverComments;
	private Integer approverId;
}
