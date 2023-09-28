package com.ftn.railwayapp.request.train;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.ftn.railwayapp.util.Constants.TIME_PATTERN;
import static com.ftn.railwayapp.util.ErrorMessages.*;

@Getter
@Setter
@AllArgsConstructor
public class StationDepartureRequest {

    @NotBlank(message = WRONG_ID)
    private String stationId;

    @NotNull(message = STARTING_STATION)
    private boolean startingStation;

    @Min(value = 0, message = WRONG_PRICE)
    private int price;

    @Min(value = 0, message = WRONG_DISCOUNT)
    private int discountIfNotStarting;

    @Min(value = 1, message = WRONG_STATION_ORDER)
    @Max(value = 6, message = WRONG_STATION_ORDER)
    private int stationOrder;

    @NotBlank(message = WRONG_LEAVING_START_TIME)
    @Pattern(regexp = TIME_PATTERN, message = WRONG_LEAVING_START_TIME)
    private String leavingTime;

}
