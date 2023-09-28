package com.ftn.railwayapp.request.train;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.ftn.railwayapp.util.Constants.NOT_WHOLE_NUMBER_REG;
import static com.ftn.railwayapp.util.ErrorMessages.WRONG_COORDINATE;
import static com.ftn.railwayapp.util.ErrorMessages.WRONG_NAME;

@Getter
@Setter
@AllArgsConstructor
public class StationRequest {

    @NotBlank(message = WRONG_NAME)
    private String name;

    @NotBlank(message = WRONG_COORDINATE)
    @Pattern(regexp = NOT_WHOLE_NUMBER_REG, message = WRONG_COORDINATE)
    private String coordinateX;

    @NotBlank(message = WRONG_COORDINATE)
    @Pattern(regexp = NOT_WHOLE_NUMBER_REG, message = WRONG_COORDINATE)
    private String coordinateY;

}
