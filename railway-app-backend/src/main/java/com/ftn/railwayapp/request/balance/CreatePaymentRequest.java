package com.ftn.railwayapp.request.balance;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.ftn.railwayapp.util.ErrorMessages.MISSING_NUM_OF_TOKENS;
import static com.ftn.railwayapp.util.ErrorMessages.WRONG_ID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {

    @NotNull(message = WRONG_ID)
    private Long balanceAccountId;

    @NotNull(message = MISSING_NUM_OF_TOKENS)
    @Positive(message = MISSING_NUM_OF_TOKENS)
    private int numOfTokens;

}
