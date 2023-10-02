package com.ftn.railwayapp.controller.train;

import com.ftn.railwayapp.request.train.StationRequest;
import com.ftn.railwayapp.response.train.StationResponse;
import com.ftn.railwayapp.service.interfaces.IStationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("station")
@Validated
public class StationController {

    private final IStationService stationService;

    public StationController(IStationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("get-all")
    @ResponseStatus(HttpStatus.OK)
    public List<StationResponse> getAll() {

        return this.stationService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StationResponse createStation(@Valid @RequestBody StationRequest stationRequest) {

        return this.stationService.createStation(
                stationRequest.getName(),
                stationRequest.getCoordinateX(),
                stationRequest.getCoordinateY()
        );
    }

}
