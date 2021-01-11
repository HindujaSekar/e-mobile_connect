package com.telecom.mobileconnection.common;

import lombok.Getter;

@Getter
public enum Availability {

    AVAILABLE("AVAILABLE"),
    NOT_AVAILABLE("NOT_AVAILABLE");

    private final String availabilityStatus;

    Availability(final String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}
