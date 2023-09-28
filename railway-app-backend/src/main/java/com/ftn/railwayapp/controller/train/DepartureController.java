package com.ftn.railwayapp.controller.train;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.InvalidDepartureDataException;
import com.ftn.railwayapp.exception.InvalidTimeException;
import com.ftn.railwayapp.request.train.DepartureRequest;
import com.ftn.railwayapp.response.train.DepartureResponse;
import com.ftn.railwayapp.service.interfaces.IDepartureService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("departure")
@Validated
public class DepartureController {

    private final IDepartureService departureService;

    public DepartureController(IDepartureService departureService) {
        this.departureService = departureService;
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
