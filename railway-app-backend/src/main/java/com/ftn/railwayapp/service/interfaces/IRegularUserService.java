package com.ftn.railwayapp.service.interfaces;


import com.ftn.railwayapp.exception.*;
import com.ftn.railwayapp.model.enums.Gender;
import com.ftn.railwayapp.model.user.RegularUser;
import com.ftn.railwayapp.response.user.UserResponse;

import java.io.IOException;

public interface IRegularUserService {
    UserResponse socialRegistration(String email, String fullName) throws EntityNotFoundException;
    RegularUser getRegularUserByEmail(String email) throws EntityNotFoundException;
    UserResponse registerRegularUser(String email, String password, String confirmPassword, String fullName, Gender gender, String city, String street, String streetNumber, String zipcode) throws PasswordsDoNotMatchException, EntityAlreadyExistException, IOException, MailCannotBeSentException, EntityNotFoundException;
    boolean activateAccount(String verifyId, int securityCode) throws EntityNotFoundException, WrongVerifyTryException;
}
