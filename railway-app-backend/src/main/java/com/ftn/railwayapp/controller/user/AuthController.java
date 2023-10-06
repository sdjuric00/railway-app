package com.ftn.railwayapp.controller.user;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.InvalidCredentialsException;
import com.ftn.railwayapp.exception.InvalidSocialTokenException;
import com.ftn.railwayapp.request.user.LoginRequest;
import com.ftn.railwayapp.response.user.LoginResponse;
import com.ftn.railwayapp.service.interfaces.IAuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ftn.railwayapp.util.ErrorMessages.INVALID_SOCIAL_TOKEN;

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

    @PostMapping(path="social-login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse socialLogin(@Valid @RequestBody @NotBlank(message = INVALID_SOCIAL_TOKEN) String token)
            throws InvalidSocialTokenException, EntityNotFoundException, InvalidCredentialsException {

        return authService.onSocialLogin(token);
    }

}
