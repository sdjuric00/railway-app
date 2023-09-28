package com.ftn.railwayapp.controller.user;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.MailCannotBeSentException;
import com.ftn.railwayapp.service.interfaces.IVerificationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("verify")
@Validated
public class VerificationController {

    private final IVerificationService verificationService;

    public VerificationController(IVerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PostMapping("send-code-again")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid @NotBlank(message = "Verify hash cannot be empty") String verifyHash)
            throws EntityNotFoundException, MailCannotBeSentException, IOException {
        this.verificationService.generateNewSecurityCode(verifyHash);
    }
}
