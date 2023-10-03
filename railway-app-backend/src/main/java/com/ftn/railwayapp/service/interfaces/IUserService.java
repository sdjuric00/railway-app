package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.PasswordsDoNotMatchException;
import com.ftn.railwayapp.model.enums.Gender;
import com.ftn.railwayapp.model.user.User;
import com.ftn.railwayapp.response.user.UserResponse;

import java.util.Optional;

public interface IUserService {

    User getVerifiedUserByEmail(String email) throws EntityNotFoundException;
    User getVerifiedRegularUserById(Long id) throws EntityNotFoundException;
    Optional<User> getVerifiedUserExistence(String email);
    boolean checkIfUserAlreadyExists(String email);
    User getVerifiedUserById(Long id) throws EntityNotFoundException;
    UserResponse update(Long id, String email, String fullName, Gender gender, String city, String street, String streetNumber, String zipcode) throws EntityNotFoundException;

    boolean changePassword(Long id, String oldPassword, String newPassword, String confirmPassword) throws EntityNotFoundException, PasswordsDoNotMatchException;
}
