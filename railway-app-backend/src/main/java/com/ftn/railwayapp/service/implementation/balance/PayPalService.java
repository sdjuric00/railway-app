package com.ftn.railwayapp.service.implementation.balance;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.PayPalPaymentException;
import com.ftn.railwayapp.service.interfaces.IBalanceAccountService;
import com.ftn.railwayapp.service.interfaces.IPayPalService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ftn.railwayapp.util.Constants.*;

@Service
public class PayPalService implements IPayPalService {

    @Value("${paypal.client.id}")
    private String clientId;
    @Value("${paypal.client.secret}")
    private String clientSecret;
    @Value("${paypal.mode}")
    private String mode;
    @Value("${paypal.currency}")
    private String currency;
    @Value(("${paypal.token.price}"))
    private int tokenPrice;

    private final IBalanceAccountService balanceAccountService;

    public PayPalService(IBalanceAccountService balanceAccountService) {
        this.balanceAccountService = balanceAccountService;
    }

    public Map<String, String> createPayment(
            final Long balanceAccountId,
            int numOfTokens
    ) throws PayPalPaymentException {
        Amount amount = createAmount(numOfTokens);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(createTransaction(amount));

        Payer payer = createPayer();
        Payment payment = createPayment(payer, transactions);
        payment.setRedirectUrls(createRedirectUrls(balanceAccountId, numOfTokens));

        return createResponse(payment);
    }

    public boolean completePayment(
            final String paymentId,
            final String payerId,
            final int numOfTokens,
            final Long tokenBankId
    )
            throws PayPalPaymentException, EntityNotFoundException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return createCompletePaymentResponse(payment, paymentExecution, numOfTokens, tokenBankId);
    }

    private Amount createAmount(final int numOfTokens) {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format("%.2f", getTotalPrice(numOfTokens, tokenPrice)));

        return amount;
    }

    private Transaction createTransaction(final Amount amount) {
        Transaction transaction = new Transaction();
        transaction.setDescription(TRANSACTION_DESCRIPTION);
        transaction.setAmount(amount);

        return transaction;
    }

    private Payer createPayer() {
        Payer payer = new Payer();
        payer.setPaymentMethod(PAYMENT_METHOD);

        return payer;
    }

    private Payment createPayment(final Payer payer, final List<Transaction> transactions) {
        Payment payment = new Payment();
        payment.setIntent(PAYMENT_INTENT);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        return  payment;
    }

    private RedirectUrls createRedirectUrls(final Long balanceAccountId, final int numOfTokens) {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(REDIRECT_URL_CANCEL);
        redirectUrls.setReturnUrl(String.format("%s/%s/%s/process", REDIRECT_URL_SUCCESS, balanceAccountId, numOfTokens));

        return redirectUrls;
    }

    private Map<String, String> createResponse(final Payment payment)
            throws PayPalPaymentException
    {
        Map<String, String> response = new HashMap<>();
        try {
            populateResponseWithLink(payment, response);
        } catch (PayPalRESTException e) {
            throw new PayPalPaymentException();
        }

        return response;
    }

    private Map<String, String> populateResponseWithLink(
            final Payment payment,
            final Map<String, String> response
    ) throws PayPalRESTException {
        Payment createdPayment;
        String redirectUrl = "";
        APIContext context = new APIContext(clientId, clientSecret, mode);
        createdPayment = payment.create(context);
        if (createdPayment!=null){
            List<Links> links = createdPayment.getLinks();
            for (Links link:links) {
                if(link.getRel().equals(PAYPAL_APPROVAL_URL)){
                    redirectUrl = link.getHref();
                    break;
                }
            }
            response.put("status", "success");
            response.put("redirectUrl", redirectUrl);
        }

        return response;
    }

    private double getTotalPrice(final double numOfTokens, final double pricePerToken) {

        return numOfTokens * pricePerToken;
    }

    private boolean createCompletePaymentResponse(
            final Payment payment,
            final PaymentExecution paymentExecution,
            final int numOfTokens,
            final Long balanceAccountId
    ) throws PayPalPaymentException, EntityNotFoundException {
        try {
            APIContext context = new APIContext(clientId, clientSecret, mode);
            Payment createdPayment = payment.execute(context, paymentExecution);
            if (createdPayment != null) {
                this.balanceAccountService.updateBalance(balanceAccountId, numOfTokens, numOfTokens*tokenPrice, currency);

                return true;
            }
        } catch (PayPalRESTException e) {
            throw new PayPalPaymentException("Payment cannot be performed. Check your balance on paypal account.");
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("balance account");
        }

        return false;
    }
}
