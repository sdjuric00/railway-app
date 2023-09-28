package com.ftn.railwayapp.service.implementation.train;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.InvalidDepartureDataException;
import com.ftn.railwayapp.exception.InvalidTimeException;
import com.ftn.railwayapp.model.train.Departure;
import com.ftn.railwayapp.model.train.StationDeparture;
import com.ftn.railwayapp.repository.train.DepartureRepository;
import com.ftn.railwayapp.request.train.StationDepartureRequest;
import com.ftn.railwayapp.response.train.DepartureResponse;
import com.ftn.railwayapp.service.interfaces.IDepartureService;
import com.ftn.railwayapp.service.interfaces.IStationService;
import com.ftn.railwayapp.service.interfaces.ITrainService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ftn.railwayapp.response.train.StationDepartureResponse.fromStationDeparture;
import static com.ftn.railwayapp.util.Helper.checkStartTimeAfterCurrent;

@Service
public class DepartureService implements IDepartureService {

    private final DepartureRepository departureRepository;

    private final ITrainService trainService;

    private final IStationService stationService;

    public DepartureService(DepartureRepository departureRepository,
                            ITrainService trainService,
                            IStationService stationService
    ) {
        this.departureRepository = departureRepository;
        this.trainService = trainService;
        this.stationService = stationService;
    }

    @Override
    public DepartureResponse createDeparture(LocalDateTime startTime,
                                             int durationInMinutes,
                                             List<StationDepartureRequest> stationDepartures,
                                             String trainId
    ) throws InvalidTimeException, EntityNotFoundException, InvalidDepartureDataException {
        if (!checkStartTimeAfterCurrent(startTime)) {
            throw new InvalidTimeException("Start time cannot be in the past.");
        }

        return DepartureResponse.fromDeparture(
          this.departureRepository.save(new Departure(
                  startTime, durationInMinutes, createStationDepartures(stationDepartures), this.trainService.getTrainById(trainId)
          ))
        );
    }

    private List<StationDeparture> createStationDepartures(List<StationDepartureRequest> stationDeparturesReq) throws EntityNotFoundException, InvalidDepartureDataException {
        List<StationDeparture> stationDepartures = new ArrayList<>();

        for (StationDepartureRequest sd : stationDeparturesReq) {
            checkStationDepartureValidity(sd);
            stationDepartures.add(new StationDeparture(
                this.stationService.getStationById(sd.getStationId()),
                sd.isStartingStation(),
                sd.getPrice(),
                sd.getDiscountIfNotStarting(),
                sd.getStationOrder(),
                sd.getLeavingTime()
            ));
        }

        return stationDepartures;
    }

    private void checkStationDepartureValidity(StationDepartureRequest stationDepartureRequest) throws InvalidDepartureDataException {
        if (stationDepartureRequest.isStartingStation() && (stationDepartureRequest.getStationOrder() > 1 ||
        stationDepartureRequest.getPrice() > 0 || stationDepartureRequest.getDiscountIfNotStarting() > 0)) {
            throw new InvalidDepartureDataException("Starting station cannot have price/discount or wrong order.");
        }

        if (stationDepartureRequest.getStationOrder() > 1 && stationDepartureRequest.getPrice() == 0) {
            throw new InvalidDepartureDataException("If station is not starting it must have selected price.");
        }
    }

}
