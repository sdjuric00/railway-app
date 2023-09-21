package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.exception.InvalidCredentialsException;
import com.ftn.railwayapp.response.LoginResponse;

public interface IAuthService {

    LoginResponse login(String email, String password) throws InvalidCredentialsException;
}
