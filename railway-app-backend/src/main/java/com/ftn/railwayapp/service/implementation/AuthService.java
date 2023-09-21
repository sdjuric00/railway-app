package com.ftn.railwayapp.service.implementation;

import com.ftn.railwayapp.exception.InvalidCredentialsException;
import com.ftn.railwayapp.response.LoginResponse;
import com.ftn.railwayapp.response.UserSecurityResponse;
import com.ftn.railwayapp.security.JWTUtils;
import com.ftn.railwayapp.security.UserPrinciple;
import com.ftn.railwayapp.service.interfaces.IAuthService;
import com.ftn.railwayapp.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Override
    public LoginResponse login(String email, String password)
            throws InvalidCredentialsException
    {
        return loginProcess(email, password);
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
        UserSecurityResponse userSecurityResponse = userPrinciple.getUser();

        return new LoginResponse(JWTUtils.generateJWT(email), userSecurityResponse);
    }

}
