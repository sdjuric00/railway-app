package com.ftn.railwayapp.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.ftn.railwayapp.util.Constants.WRONG_SECURITY_CODE;
import static com.ftn.railwayapp.util.Constants.WRONG_VERIFY_CODE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyRequest {
    @NotBlank(message = WRONG_VERIFY_CODE)
    @NotNull(message = WRONG_VERIFY_CODE)
    private String verifyId;

    @NotNull(message = WRONG_SECURITY_CODE)
    @Positive(message = WRONG_SECURITY_CODE)
    private int securityCode;
}
