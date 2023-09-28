package com.ftn.railwayapp.controller.user;

import com.ftn.railwayapp.exception.*;
import com.ftn.railwayapp.request.user.UserRegistrationRequest;
import com.ftn.railwayapp.request.user.VerifyRequest;
import com.ftn.railwayapp.response.user.UserResponse;
import com.ftn.railwayapp.service.interfaces.IRegularUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("regular-user")
@Validated
public class RegularUserController {

    private final IRegularUserService regularUserService;

    public RegularUserController(IRegularUserService regularUserService) {
        this.regularUserService = regularUserService;
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
