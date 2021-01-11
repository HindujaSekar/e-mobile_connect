package com.telecom.mobileconnection.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApproveRequestDTO {

 public String Status;
public String approverComments;
public Integer approverId;
}
