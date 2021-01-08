package com.telecom.mobileconnection.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subscriptionId;
    private Integer mobileId;
    private String status;
    private Integer userId;
    private Integer planId;
    private Integer approverId;
    private String approverComments;
    private LocalDate registerDate;
}
