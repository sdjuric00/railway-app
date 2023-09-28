package com.ftn.railwayapp.response.train;

import com.ftn.railwayapp.model.train.Departure;

import java.time.LocalDateTime;
import java.util.List;


public record DepartureResponse(String id, LocalDateTime startTime, int durationInMinutes,
                                int delayedMinutes, boolean cancelled, List<StationDepartureResponse> stationDepartures, TrainBasicDataResponse train,
                                int numOfOccupiedSeats) {

    public static DepartureResponse fromDeparture(Departure departure) {
        List<StationDepartureResponse> stationDepartures = departure.getStationDepartures().stream()
                .map(StationDepartureResponse::fromStationDeparture).toList();

        return new DepartureResponse(departure.getId(), departure.getStartTime(), departure.getDurationInMinutes(),
                departure.getDelayedMinutes(), departure.isCancelled(), stationDepartures, TrainBasicDataResponse.fromTrain(departure.getTrain()),
                departure.getOccupiedSeats().size());
    }
}
