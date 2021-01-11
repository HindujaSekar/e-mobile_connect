package com.telecom.mobileconnection.exception;

public class SubscriptionNotFoundException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public SubscriptionNotFoundException(String message)
    {
        super(message);
    }
}
 
