package com.ftn.railwayapp.service.implementation;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.model.user.User;
import com.ftn.railwayapp.repository.UserRepository;
import com.ftn.railwayapp.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getVerifiedUser(String email) throws EntityNotFoundException {
        return userRepository.getVerifiedUser(email)
                .orElseThrow(() -> new EntityNotFoundException("user"));
    }

    @Override
    public Optional<User> getVerifiedUserExistance(String email) {
        return userRepository.getVerifiedUser(email);
    }

}
