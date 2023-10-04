package com.ftn.railwayapp.exception;

import static com.ftn.railwayapp.util.ExceptionMessages.PAYPAL_PAYMENT_EXCEPTION;

public class PayPalPaymentException extends AppException{

    public PayPalPaymentException() {
        super(PAYPAL_PAYMENT_EXCEPTION);
    }

    public PayPalPaymentException(String message) {
        super(message);
    }
}
