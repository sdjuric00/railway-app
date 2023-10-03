package com.ftn.railwayapp.controller.user;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.OperationCannotBeCompletedException;
import com.ftn.railwayapp.exception.PasswordsDoNotMatchException;
import com.ftn.railwayapp.request.user.ChangePasswordRequest;
import com.ftn.railwayapp.request.user.UserRequest;
import com.ftn.railwayapp.response.user.UserResponse;
import com.ftn.railwayapp.service.interfaces.IUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ftn.railwayapp.util.ErrorMessages.WRONG_ID;

@RestController
@RequestMapping("user")
@Validated
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_REGULAR')")
    public UserResponse getById(@PathVariable @NotNull(message = WRONG_ID) Long id) throws EntityNotFoundException {

        return UserResponse.fromUser(userService.getVerifiedUserById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_REGULAR')")
    public UserResponse update(
            @PathVariable @NotNull(message = WRONG_ID) Long id,
            @RequestBody @Valid UserRequest userRequest)
    throws EntityNotFoundException {

        return this.userService.update(
                id,
                userRequest.getFullName(),
                userRequest.getGender(),
                userRequest.getCity(),
                userRequest.getStreet(),
                userRequest.getStreetNumber(),
                userRequest.getZipcode()
        );
    }

    @PutMapping("/change-password/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_REGULAR')")
    public boolean changePassword(
            @PathVariable @NotNull(message = WRONG_ID) Long id,
            @RequestBody @Valid ChangePasswordRequest changePasswordRequest
    ) throws PasswordsDoNotMatchException, EntityNotFoundException, OperationCannotBeCompletedException {

        return this.userService.changePassword(
                id,
                changePasswordRequest.getOldPassword(),
                changePasswordRequest.getNewPassword(),
                changePasswordRequest.getConfirmPassword()
        );
    }

}
