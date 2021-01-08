package com.telecom.mobileconnection.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String address;
    private String emailId;
    private String aadharNo;
    private String alternateNumber;
}
