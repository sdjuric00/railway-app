package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.model.user.User;

import java.util.Optional;

public interface IUserService {

    User getVerifiedUser(String email) throws EntityNotFoundException;
    User getVerifiedRegularUserById(Long id) throws EntityNotFoundException;
    Optional<User> getVerifiedUserExistence(String email);
    boolean checkIfUserAlreadyExists(String email);
}
