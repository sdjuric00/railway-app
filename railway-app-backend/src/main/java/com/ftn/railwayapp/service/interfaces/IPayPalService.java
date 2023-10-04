package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.PayPalPaymentException;

import java.util.Map;

public interface IPayPalService {

    Map<String, String> createPayment(
            Long balanceAccountId,
            int numOfTokens
    ) throws EntityNotFoundException, PayPalPaymentException;

    boolean completePayment(
            final String paymentId,
            final String payerId,
            final int numOfTokens,
            final Long balanceAccountId
    ) throws PayPalPaymentException, EntityNotFoundException;

}
