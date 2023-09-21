package com.ftn.railwayapp.service.implementation;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.model.user.User;
import com.ftn.railwayapp.response.UserResponse;
import com.ftn.railwayapp.security.UserPrinciple;
import com.ftn.railwayapp.service.interfaces.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserService userService;

    public CustomUserDetailsService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {
        UserResponse userResponse;
        try {
            User user = userService.getVerifiedUser(email);
            userResponse = new UserResponse(user.getEmail(), user.getPassword(), user.getRole());
        } catch (EntityNotFoundException e) {
            return null;
        }

        return new UserPrinciple(userResponse);
    }

}
