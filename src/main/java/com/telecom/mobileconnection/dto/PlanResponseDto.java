package com.telecom.mobileconnection.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanResponseDto {
    private Integer planId;
    private String planDescription;
    private Integer validity;
    private Double price;

}
