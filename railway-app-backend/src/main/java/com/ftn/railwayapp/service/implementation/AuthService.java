package com.ftn.railwayapp.service.implementation;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.InvalidCredentialsException;
import com.ftn.railwayapp.exception.InvalidSocialTokenException;
import com.ftn.railwayapp.model.user.User;
import com.ftn.railwayapp.response.LoginResponse;
import com.ftn.railwayapp.response.UserLoginResponse;
import com.ftn.railwayapp.response.UserResponse;
import com.ftn.railwayapp.security.JWTUtils;
import com.ftn.railwayapp.security.UserPrinciple;
import com.ftn.railwayapp.service.interfaces.IAuthService;
import com.ftn.railwayapp.service.interfaces.IRegularUserService;
import com.ftn.railwayapp.service.interfaces.IUserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;

    private final IUserService userService;

    private final IRegularUserService regularUserService;

    public AuthService(AuthenticationManager authenticationManager,
                       IUserService userService,
                       IRegularUserService regularUserService
    ) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.regularUserService = regularUserService;
    }

    @Override
    public LoginResponse login(String email, String password)
            throws InvalidCredentialsException
    {
        return loginProcess(email, password);
    }

    @Override
    public LoginResponse onSocialLogin(String token)
            throws InvalidSocialTokenException, EntityNotFoundException, InvalidCredentialsException
    {
        GoogleIdToken.Payload payload;

        try {
            payload = parseToken(token);
        } catch (IOException e) {
            throw new InvalidSocialTokenException();
        }

        return logInOrRegister(payload);
    }

    private LoginResponse logInOrRegister(GoogleIdToken.Payload payload)
            throws EntityNotFoundException, InvalidCredentialsException
    {
        Optional<User> user = userService.getVerifiedUserExistance(payload.getEmail());

        return user.isPresent() ? this.socialLogin(UserResponse.fromUser(user.get()))
                : this.socialLogin(regularUserService.socialRegistration(payload.getEmail(), (String) payload.get("name")));
    }

    private LoginResponse socialLogin(UserResponse user) throws InvalidCredentialsException {
        if (!user.socialAccount()) {throw new InvalidCredentialsException();}
        final String token = JWTUtils.generateJWT(user.email());

        return new LoginResponse(token, new UserLoginResponse(user.id(), user.email(), user.role(), user.socialAccount()));
    }

    private GoogleIdToken.Payload parseToken(String token) throws IOException {
        NetHttpTransport transport = new NetHttpTransport();
        GsonFactory factory = GsonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder tokenVerifier =
                new GoogleIdTokenVerifier.Builder(transport,factory)
                        .setAudience(Collections.singleton(token));

        GoogleIdToken googleIdToken = GoogleIdToken.parse(
                tokenVerifier.getJsonFactory(),
                token
        );

        return googleIdToken.getPayload();
    }

    private LoginResponse loginProcess(String email, String password)
            throws InvalidCredentialsException
    {
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (Exception e) {
            throw new InvalidCredentialsException();
        }

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserPrinciple userPrinciple = (UserPrinciple) authenticate.getPrincipal();
        UserLoginResponse userLoginResponse = new UserLoginResponse(userPrinciple.getUser().id(),
                userPrinciple.getUser().email(), userPrinciple.getUser().role(), userPrinciple.getUser().socialAccount());

        return new LoginResponse(JWTUtils.generateJWT(email), userLoginResponse);
    }

}
