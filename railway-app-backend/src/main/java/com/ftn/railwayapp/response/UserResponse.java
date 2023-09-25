package com.ftn.railwayapp.response;

import com.ftn.railwayapp.model.enums.Gender;
import com.ftn.railwayapp.model.user.Address;
import com.ftn.railwayapp.model.user.RegularUser;
import com.ftn.railwayapp.model.user.Role;
import com.ftn.railwayapp.model.user.User;

public record UserResponse(Long id, String email, String fullName, Gender gender, Address address, Role role, boolean socialAccount) {

    public static UserResponse fromUser(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getFullName(), user.getGender(), user.getAddress(), user.getRole(), user.isSocialAccount());
    }

    public static UserResponse fromRegularUser(RegularUser user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getFullName(), user.getGender(), user.getAddress(), user.getRole(), user.isSocialAccount());
    }
}
