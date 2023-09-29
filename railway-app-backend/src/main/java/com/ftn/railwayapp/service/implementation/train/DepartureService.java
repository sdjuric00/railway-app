package com.ftn.railwayapp.service.implementation.train;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.InvalidDepartureDataException;
import com.ftn.railwayapp.exception.InvalidTimeException;
import com.ftn.railwayapp.exception.OperationCannotBeCompletedException;
import com.ftn.railwayapp.model.enums.TrainBenefits;
import com.ftn.railwayapp.model.train.Departure;
import com.ftn.railwayapp.model.train.StationDeparture;
import com.ftn.railwayapp.model.train.Train;
import com.ftn.railwayapp.repository.train.DepartureRepository;
import com.ftn.railwayapp.request.train.StationDepartureRequest;
import com.ftn.railwayapp.response.train.DepartureResponse;
import com.ftn.railwayapp.service.interfaces.IDepartureService;
import com.ftn.railwayapp.service.interfaces.IStationService;
import com.ftn.railwayapp.service.interfaces.ITrainService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
    public Page<DepartureResponse> departuresTimetable(int page,
                                                       int pageSize,
                                                       String trainType,
                                                       String startingStationId,
                                                       String destinationStationId,
                                                       LocalDateTime startTime,
                                                       String benefits
    ) throws OperationCannotBeCompletedException {
        checkFilteringParameters(trainType, startTime, benefits);

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("startTime").ascending());
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);
        List<String> validTrainsIDs = this.trainService.findTrainsByTypeAndBenefits(trainType, benefits)
                .stream().map(Train::getId).toList();

        Page<Departure> departuresRegardlessOfStations = this.departureRepository.filterDeparturesRegardlessOfBenefits(startTime, endOfDay, validTrainsIDs, pageable);

        return filterStations(departuresRegardlessOfStations, startingStationId, destinationStationId, pageable);
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

    private void checkFilteringParameters(String trainType, LocalDateTime time, String benefits)
            throws OperationCannotBeCompletedException
    {
        if (isTrainTypeInvalid(trainType) || isFilteringTimeInvalid(time) || areBenefitsInvalid(benefits)) {
            throw new OperationCannotBeCompletedException("Filtering data is invalid.");
        }
    }

    private boolean isTrainTypeInvalid(String trainType) {
        return switch (trainType.toUpperCase(Locale.ROOT)) {
            case "ALL", "SOKO", "REGIO" -> false;
            default -> true;
        };
    }

    private boolean isFilteringTimeInvalid(LocalDateTime time) {
        return time.isBefore(LocalDateTime.now().minusDays(1));
    }

    private boolean areBenefitsInvalid(String benefits) {
        if (benefits.equalsIgnoreCase("ALL")) return false;
        try {
            String[] benefitsArray = benefits.split(",");
            Arrays.stream(benefitsArray).map(TrainBenefits::valueOf);
        } catch (IllegalArgumentException exception) {
            return true;
        }

        return false;
    }

    private Page<DepartureResponse> filterStations(Page<Departure> departuresRegardlessOfStations,
                                                   String startingStationId,
                                                   String destinationStationId,
                                                   Pageable pageable
    ) {
        List<Departure> departures = new ArrayList<>(departuresRegardlessOfStations.getContent());
        List<DepartureResponse> filteredDepartures = new ArrayList<>();

        for (Departure departure : departures) {
            boolean foundStarting = false;
            boolean foundDestination = false;
            for (StationDeparture stationDeparture : departure.getStationDepartures()) {
                if (stationDeparture.getStation().getId().equals(startingStationId) && !foundDestination) {
                    foundStarting = true;
                } else if (stationDeparture.getStation().getId().equals(destinationStationId) && foundStarting) {
                    foundDestination = true;
                }
            }

            if (foundStarting && foundDestination) {
                filteredDepartures.add(DepartureResponse.fromDeparture(departure));
            }
        }

        return new PageImpl<DepartureResponse>(filteredDepartures, pageable, departures.size());
    }

}
