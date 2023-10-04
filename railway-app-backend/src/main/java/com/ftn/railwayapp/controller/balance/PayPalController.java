package com.ftn.railwayapp.controller.balance;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.PayPalPaymentException;
import com.ftn.railwayapp.request.balance.CompletePaymentRequest;
import com.ftn.railwayapp.request.balance.CreatePaymentRequest;
import com.ftn.railwayapp.service.interfaces.IPayPalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("paypal")
@Validated
public class PayPalController {

    private final IPayPalService payPalService;

    public PayPalController(IPayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @PostMapping("create-payment")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_REGULAR')")
    public Map<String, String> createPayment(@RequestBody @Valid CreatePaymentRequest createPaymentRequest)
            throws PayPalPaymentException, EntityNotFoundException {

        return payPalService.createPayment(
                createPaymentRequest.getBalanceAccountId(),
                createPaymentRequest.getNumOfTokens()
        );
    }

    @PostMapping("complete-payment")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_REGULAR')")
    public boolean completePayment(@RequestBody CompletePaymentRequest completePaymentRequest)
            throws PayPalPaymentException, EntityNotFoundException {

        return payPalService.completePayment(
                completePaymentRequest.getPaymentId(),
                completePaymentRequest.getPayerId(),
                completePaymentRequest.getNumOfTokens(),
                completePaymentRequest.getAccountBalanceId()
        );
    }
}
