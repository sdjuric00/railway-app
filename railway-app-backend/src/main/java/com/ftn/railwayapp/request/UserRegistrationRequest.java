package com.ftn.railwayapp.request;

import com.ftn.railwayapp.model.enums.Gender;
import com.ftn.railwayapp.util.Constants;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import static com.ftn.railwayapp.util.Constants.*;
import static com.ftn.railwayapp.util.ErrorMessages.*;

@Getter
@Setter
public class UserRegistrationRequest extends UserRequest {


    @NotBlank(message = WRONG_PASSWORD)
    @Pattern(regexp = LEGIT_PASSWORD_REG, message = WRONG_PASSWORD)
    @Size(min = 6, max = 50, message = PASSWORD_NOT_LONG_ENOUGH)
    private String password;

    @NotBlank(message = WRONG_PASSWORD)
    @Pattern(regexp = LEGIT_PASSWORD_REG, message = WRONG_PASSWORD)
    @Size(min = 6, max = 50, message = PASSWORD_NOT_LONG_ENOUGH)
    private String confirmPassword;

    public UserRegistrationRequest(@NotBlank(message = EMPTY_EMAIL) @Email(message = WRONG_EMAIL) String email,
                                   @NotBlank(message = WRONG_FULL_NAME) @Pattern(regexp = Constants.LEGIT_FULL_NAME_REG, message = WRONG_FULL_NAME) String fullName,
                                   @NotBlank(message = WRONG_CITY) @Pattern(regexp = LEGIT_CITY_AND_STREET_REG, message = WRONG_CITY) String city,
                                   @NotNull(message = WRONG_GENDER) Gender gender,
                                   @NotBlank(message = WRONG_STREET) @Pattern(regexp = LEGIT_CITY_AND_STREET_REG, message = WRONG_STREET) String street,
                                   @NotBlank(message = WRONG_STREET_NUMBER) @Size(min = 2, max = 10) String streetNumber,
                                   @NotBlank(message = WRONG_ZIPCODE) @Pattern(regexp = ZIPCODE_REG, message = WRONG_ZIPCODE) String zipcode,
                                   @NotBlank(message = WRONG_PASSWORD) @Pattern(regexp = LEGIT_PASSWORD_REG, message = WRONG_PASSWORD) @Size(min = 6, max = 50, message = PASSWORD_NOT_LONG_ENOUGH) String password,
                                   @NotBlank(message = WRONG_PASSWORD) @Pattern(regexp = LEGIT_PASSWORD_REG, message = WRONG_PASSWORD) @Size(min = 6, max = 50, message = PASSWORD_NOT_LONG_ENOUGH) String confirmPassword
    ) {
        super(email, fullName, gender, city, street, streetNumber, zipcode);
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
