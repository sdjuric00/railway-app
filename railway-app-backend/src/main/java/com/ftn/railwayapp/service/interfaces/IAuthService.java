package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.InvalidCredentialsException;
import com.ftn.railwayapp.exception.InvalidSocialTokenException;
import com.ftn.railwayapp.response.user.LoginResponse;


public interface IAuthService {

    LoginResponse login(String email, String password) throws InvalidCredentialsException;

    LoginResponse onSocialLogin(String token) throws InvalidSocialTokenException, EntityNotFoundException, InvalidCredentialsException;

}
