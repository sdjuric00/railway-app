package com.ftn.railwayapp.service.interfaces;


import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.response.UserResponse;

public interface IRegularUserService {
    UserResponse socialRegistration(String email, String fullName) throws EntityNotFoundException;
}
