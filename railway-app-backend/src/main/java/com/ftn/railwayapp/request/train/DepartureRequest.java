package com.ftn.railwayapp.request.train;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.ftn.railwayapp.util.ErrorMessages.*;


@Getter
@Setter
@AllArgsConstructor
public class DepartureRequest {

    @NotNull(message = WRONG_START_TIME)
    private LocalDateTime startTime;

    @Positive(message = WRONG_DURATION_IN_MINUTES)
    @Min(value = 10, message = WRONG_DURATION_IN_MINUTES)
    @Max(value = 1440, message = WRONG_DURATION_IN_MINUTES)
    private int durationInMinutes;

    @NotEmpty(message = WRONG_STATION_DEPARTURES)
    @Valid
    private List<StationDepartureRequest> stationDepartures = new ArrayList<>();

    @NotBlank(message = WRONG_ID)
    private String trainId;

}
