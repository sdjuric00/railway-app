package com.ftn.railwayapp.service.implementation.user;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.model.user.User;
import com.ftn.railwayapp.response.user.UserSecurityResponse;
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
        UserSecurityResponse userSecurityResponse;
        try {
            User user = userService.getVerifiedUserByEmail(email);
            userSecurityResponse = new UserSecurityResponse(user.getId(), user.getEmail(),
                    user.getPassword(), user.getRole(), user.isSocialAccount());
        } catch (EntityNotFoundException e) {
            return null;
        }

        return new UserPrinciple(userSecurityResponse);
    }

}
