package com.telecom.mobileconnection.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlanResponseDto {
    private Integer planId;
    private String planDescription;
    private Integer validity;
    private Double price;

}
