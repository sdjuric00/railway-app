package com.ftn.railwayapp.controller.user;

import com.ftn.railwayapp.exception.*;
import com.ftn.railwayapp.request.user.UserRegistrationRequest;
import com.ftn.railwayapp.request.user.VerifyRequest;
import com.ftn.railwayapp.response.user.UserResponse;
import com.ftn.railwayapp.service.interfaces.IRegularUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.ftn.railwayapp.util.ErrorMessages.WRONG_ID;


@RestController
@RequestMapping("regular-user")
@Validated
public class RegularUserController {

    private final IRegularUserService regularUserService;

    public RegularUserController(IRegularUserService regularUserService) {
        this.regularUserService = regularUserService;
    }

    @GetMapping("/balance-account/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_REGULAR')")
    public Long getBalanceAccountId(@PathVariable @Valid @NotNull(message = WRONG_ID) Long id)
            throws EntityNotFoundException
    {

        return this.regularUserService.getBalanceAccountId(id);
    }

    @PostMapping(path="register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody UserRegistrationRequest userReq)
            throws EntityNotFoundException, PasswordsDoNotMatchException, EntityAlreadyExistException, IOException, MailCannotBeSentException
    {

        return this.regularUserService.registerRegularUser(
                userReq.getEmail(),
                userReq.getPassword(),
                userReq.getConfirmPassword(),
                userReq.getFullName(),
                userReq.getGender(),
                userReq.getCity(),
                userReq.getStreet(),
                userReq.getStreetNumber(),
                userReq.getZipcode()
        );
    }

    @PutMapping("/activate-account")
    @ResponseStatus(HttpStatus.OK)
    public boolean update(@Valid @RequestBody VerifyRequest verifyRequest)
            throws EntityNotFoundException, WrongVerifyTryException
    {
        return regularUserService.activateAccount(verifyRequest.getVerifyId(), verifyRequest.getSecurityCode());
    }

}
