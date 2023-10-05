package com.ftn.railwayapp.service.implementation.train;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.InvalidDepartureDataException;
import com.ftn.railwayapp.exception.InvalidTimeException;
import com.ftn.railwayapp.exception.OperationCannotBeCompletedException;
import com.ftn.railwayapp.model.train.Departure;
import com.ftn.railwayapp.model.train.StationDeparture;
import com.ftn.railwayapp.model.train.Train;
import com.ftn.railwayapp.repository.train.DepartureRepository;
import com.ftn.railwayapp.request.train.StationDepartureRequest;
import com.ftn.railwayapp.response.train.DepartureDetailsResponse;
import com.ftn.railwayapp.response.train.DepartureResponse;
import com.ftn.railwayapp.response.train.DepartureSearchResponse;
import com.ftn.railwayapp.service.interfaces.IDepartureService;
import com.ftn.railwayapp.service.interfaces.IStationService;
import com.ftn.railwayapp.service.interfaces.ITrainService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.ftn.railwayapp.util.Constants.DELAYED_MINUTES;
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
    public Departure getById(String id) throws EntityNotFoundException {

        return this.departureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("departure"));
    }

    @Override
    public Page<DepartureSearchResponse> departuresTimetable(int page,
                                                       int pageSize,
                                                       String trainType,
                                                       String startingStationId,
                                                       String destinationStationId,
                                                       LocalDateTime startTime
    ) throws OperationCannotBeCompletedException {
        checkFilteringParameters(trainType, startTime);

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("startTime").ascending());
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);
        List<String> validTrainsIDs = this.trainService.findTrainsByType(trainType)
                .stream().map(Train::getId).toList();

        Page<Departure> departures = this.departureRepository.filterDepartures(startTime, endOfDay, validTrainsIDs, pageable);

        return filterStations(departures, startingStationId, destinationStationId, departures.getTotalElements(), pageable);
    }

    @Override
    public DepartureDetailsResponse getDepartureDetails(String departureId, String startingStationId, String destinationStationId) throws EntityNotFoundException, OperationCannotBeCompletedException {
        Departure departure = this.getById(departureId);
        int totalNumOfSeats = this.trainService.getTotalNumOfSeats(departure.getTrain().getId());
        StationDeparture startingStationDeparture = getStationDepartureFromDeparture(departure, startingStationId);
        StationDeparture destinationStationDeparture = getStationDepartureFromDeparture(departure, destinationStationId);
        checkStationsOrder(startingStationDeparture.getStationOrder(), destinationStationDeparture.getStationOrder());

        return DepartureDetailsResponse.fromDeparture(departure, startingStationDeparture, destinationStationDeparture, totalNumOfSeats);
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

    @Override
    public StationDeparture getStationDepartureFromDeparture(Departure departure, String stationId)
            throws OperationCannotBeCompletedException
    {
        for (StationDeparture stationDeparture : departure.getStationDepartures()) {
            if (stationDeparture.getStation().getId().equals(stationId)) {
                return stationDeparture;
            }
        }

        throw new OperationCannotBeCompletedException("Station is not valid.");
    }

    @Override
    public void checkDepartureInThePast(LocalDateTime startTime) throws InvalidTimeException {
        if (startTime.isBefore(LocalDateTime.now())) {
            throw new InvalidTimeException("Departure is in the past");
        }
    }

    @Override
    public List<String> getDepartureIdsForFuture5Hours() {

        return this.departureRepository.getDepartureIdsForFuture5Hours(LocalDateTime.now(), LocalDateTime.now().plusHours(5));
    }

    @Override
    public void delayDeparture(String id) throws EntityNotFoundException {
        Departure departure = getById(id);
        departure.setDelayedMinutes(DELAYED_MINUTES);
        this.departureRepository.save(departure);
    }

    @Override
    public void cancelDeparture(String id) throws EntityNotFoundException {
        Departure departure = getById(id);
        departure.setCancelled(true);
        this.departureRepository.save(departure);
    }

    @Override
    public void checkStationsOrder(int startingStationOrder, int destinationStationOrder) throws OperationCannotBeCompletedException {
        if (startingStationOrder >= destinationStationOrder) {
            throw new OperationCannotBeCompletedException("Station is not valid.");
        }
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

    private void checkFilteringParameters(String trainType, LocalDateTime time)
            throws OperationCannotBeCompletedException
    {
        if (isTrainTypeInvalid(trainType) || isFilteringTimeInvalid(time)) {
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

    private Page<DepartureSearchResponse> filterStations(Page<Departure> departuresRegardlessOfStations,
                                                   String startingStationId,
                                                   String destinationStationId,
                                                   long totalElements,
                                                   Pageable pageable
    ) {
        List<Departure> departures = new ArrayList<>(departuresRegardlessOfStations.getContent());
        List<DepartureSearchResponse> filteredDepartures = new ArrayList<>();

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
                filteredDepartures.add(DepartureSearchResponse.fromDeparture(departure, startingStationId, destinationStationId));
            } else { totalElements = totalElements - 1; }
        }

        return new PageImpl<DepartureSearchResponse>(filteredDepartures, pageable, totalElements);
    }

}
