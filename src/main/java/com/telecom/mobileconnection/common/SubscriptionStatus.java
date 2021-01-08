package com.telecom.mobileconnection.common;

import lombok.Getter;

@Getter
public enum SubscriptionStatus {

    PROGRESS("IN_PROGRESS"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    REFER_BACK("REFER_BACK"),
    CONNECTION_ENABLED("CONNECTION_ENABLED");
    private final String status;

    SubscriptionStatus(final String status) {
        this.status = status;
    }


}
