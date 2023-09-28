package com.ftn.railwayapp.response.user;

import com.ftn.railwayapp.model.user.Role;

public record UserLoginResponse(Long id, String email, Role role, boolean socialAccount) {
}
