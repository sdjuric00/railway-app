package com.ftn.railwayapp.controller.train;

import com.ftn.railwayapp.request.train.TrainRequest;
import com.ftn.railwayapp.response.train.TrainResponse;
import com.ftn.railwayapp.service.interfaces.ITrainService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("train")
@Validated
public class TrainController {

    private final ITrainService trainService;

    public TrainController(ITrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public TrainResponse createTrain(@Valid @RequestBody TrainRequest trainRequest) {

        return trainService.createTrain(
                trainRequest.getName(),
                trainRequest.getType(),
                trainRequest.getWagons(),
                trainRequest.getTrainBenefits()
        );
    }
}
