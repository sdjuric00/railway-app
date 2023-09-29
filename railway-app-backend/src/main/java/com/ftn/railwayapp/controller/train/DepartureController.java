package com.ftn.railwayapp.controller.train;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.InvalidDepartureDataException;
import com.ftn.railwayapp.exception.InvalidTimeException;
import com.ftn.railwayapp.exception.OperationCannotBeCompletedException;
import com.ftn.railwayapp.request.train.DepartureRequest;
import com.ftn.railwayapp.response.train.DepartureResponse;
import com.ftn.railwayapp.service.interfaces.IDepartureService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("departure")
@Validated
public class DepartureController {

    private final IDepartureService departureService;

    public DepartureController(IDepartureService departureService) {
        this.departureService = departureService;
    }


    @GetMapping("timetable")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_REGULAR')")
    public Page<DepartureResponse> departuresTimetable(
            @RequestParam("page") @Min(0) int page,
            @RequestParam("pageSize") @Min(5) @Max(10) int pageSize,
            @RequestParam("trainType") @NotBlank String trainType,
            @RequestParam("startingStation") @NotBlank String startingStationId,
            @RequestParam("destinationStation") @NotBlank String destinationStationId,
            @RequestParam("time") @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time,
            @RequestParam("benefits") @NotBlank String benefits
    ) throws OperationCannotBeCompletedException {

        return departureService.departuresTimetable(
                page, pageSize, trainType, startingStationId, destinationStationId, time, benefits
        );
    }

    //only for testing and populating MongoDB
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public DepartureResponse createDeparture(@Valid @RequestBody DepartureRequest departureRequest)
            throws InvalidTimeException, EntityNotFoundException, InvalidDepartureDataException {

        return departureService.createDeparture(
          departureRequest.getStartTime(),
          departureRequest.getDurationInMinutes(),
          departureRequest.getStationDepartures(),
          departureRequest.getTrainId()
        );
    }

}
