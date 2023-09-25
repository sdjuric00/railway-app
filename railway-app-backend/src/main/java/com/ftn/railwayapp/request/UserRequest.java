package com.ftn.railwayapp.request;

import com.ftn.railwayapp.model.enums.Gender;
import com.ftn.railwayapp.util.Constants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.ftn.railwayapp.util.Constants.LEGIT_CITY_AND_STREET_REG;
import static com.ftn.railwayapp.util.Constants.ZIPCODE_REG;
import static com.ftn.railwayapp.util.ErrorMessages.*;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = EMPTY_EMAIL)
    @Email(message = WRONG_EMAIL)
    private final String email;

    @NotBlank(message = WRONG_FULL_NAME)
    @Pattern(regexp = Constants.LEGIT_FULL_NAME_REG, message = WRONG_FULL_NAME)
    private final String fullName;

    @NotNull(message = WRONG_GENDER)
    private final Gender gender;

    @NotBlank(message = WRONG_CITY)
    @Pattern(regexp = LEGIT_CITY_AND_STREET_REG, message = WRONG_CITY)
    private final String city;

    @NotBlank(message = WRONG_STREET)
    @Pattern(regexp = LEGIT_CITY_AND_STREET_REG, message = WRONG_STREET)
    private final String street;

    @NotBlank(message = WRONG_STREET_NUMBER)
    @Size(min = 1, max = 10, message = WRONG_STREET_NUMBER)
    private final String streetNumber;

    @NotBlank(message = WRONG_ZIPCODE)
    @Pattern(regexp = ZIPCODE_REG, message = WRONG_ZIPCODE)
    private final String zipcode;
}
