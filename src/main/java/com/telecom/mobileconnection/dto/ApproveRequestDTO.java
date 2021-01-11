package com.telecom.mobileconnection.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApproveRequestDTO {

 public String status;
public String approverComments;
public Integer approverId;
}
