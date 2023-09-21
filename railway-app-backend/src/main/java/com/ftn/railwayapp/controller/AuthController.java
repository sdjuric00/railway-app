package com.ftn.railwayapp.controller;

import com.ftn.railwayapp.exception.InvalidCredentialsException;
import com.ftn.railwayapp.request.LoginRequest;
import com.ftn.railwayapp.response.LoginResponse;
import com.ftn.railwayapp.service.interfaces.IAuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@Validated
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path="login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@Valid @RequestBody final LoginRequest loginRequest)
            throws InvalidCredentialsException {

        return authService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

}
