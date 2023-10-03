package com.ftn.railwayapp.service.implementation.user;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.OperationCannotBeCompletedException;
import com.ftn.railwayapp.exception.PasswordsDoNotMatchException;
import com.ftn.railwayapp.model.enums.Gender;
import com.ftn.railwayapp.model.user.User;
import com.ftn.railwayapp.repository.user.UserRepository;
import com.ftn.railwayapp.response.user.UserResponse;
import com.ftn.railwayapp.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ftn.railwayapp.util.Helper.*;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getVerifiedUserByEmail(String email) throws EntityNotFoundException {
        return userRepository.getVerifiedUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("user"));
    }

    @Override
    public User getVerifiedUserById(Long id) throws EntityNotFoundException {
        return userRepository.getVerifiedUserById(id)
                .orElseThrow(() -> new EntityNotFoundException("user"));
    }

    @Override
    public User getVerifiedRegularUserById(Long id) throws EntityNotFoundException {
        return userRepository.getVerifiedRegularUserById(id)
                .orElseThrow(() -> new EntityNotFoundException("user"));
    }

    @Override
    public Optional<User> getVerifiedUserExistence(String email) {
        return userRepository.getVerifiedUserByEmail(email);
    }

    @Override
    public boolean checkIfUserAlreadyExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public UserResponse update(Long id,
                               String fullName,
                               Gender gender,
                               String city,
                               String street,
                               String streetNumber,
                               String zipcode
    ) throws EntityNotFoundException {
        User user = getVerifiedUserById(id);
        user.setFullName(fullName);
        user.setGender(gender);
        user.getAddress().setCity(city);
        user.getAddress().setStreet(street);
        user.getAddress().setStreetNumber(streetNumber);
        user.getAddress().setZipcode(zipcode);

        return UserResponse.fromUser(this.userRepository.saveAndFlush(user));
    }

    @Override
    public boolean changePassword(Long id,
                                  String oldPassword,
                                  String newPassword,
                                  String confirmPassword
    ) throws EntityNotFoundException, PasswordsDoNotMatchException, OperationCannotBeCompletedException {
        User user = getVerifiedUserById(id);
        if (user.isSocialAccount()) {
            throw new OperationCannotBeCompletedException("Social account logged users cannot change password.");
        }

        checkPasswordsMatching(newPassword, confirmPassword, user, oldPassword);

        user.setPassword(getHash(newPassword));
        this.userRepository.save(user);

        return true;
    }

    private void checkPasswordsMatching(String newPassword, String confirmPassword, User user, String oldPassword) throws PasswordsDoNotMatchException {
        if (!oldPasswordsMatch(oldPassword, user.getPassword())) {
            throw new PasswordsDoNotMatchException("Your old password is not correct!");
        } else if (passwordsDontMatch(newPassword, confirmPassword)) {
            throw new PasswordsDoNotMatchException("New password and confirm password don't match.");
        }
    }

}
