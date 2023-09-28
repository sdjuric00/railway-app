package com.ftn.railwayapp.response.user;

import com.ftn.railwayapp.model.user.Role;

public record UserSecurityResponse(Long id, String email, String password, Role role, boolean socialAccount) {
}
