package com.ftn.railwayapp.response.train;

import com.ftn.railwayapp.model.train.Departure;
import com.ftn.railwayapp.model.train.StationDeparture;

import java.util.List;

public record DepartureDetailsResponse(String id, TrainBasicDataResponse train,
                                       StationDepartureResponse startingStation, StationDepartureResponse destinationStation,
                                       List<StationDepartureResponse> allStations, int totalNumOfSeats) {

    public static DepartureDetailsResponse fromDeparture(Departure departure,
                                                         StationDeparture startingStation,
                                                         StationDeparture destinationStation,
                                                         int totalNumOfSeats
    ) {
        List<StationDepartureResponse> allStations = departure.getStationDepartures().stream().map(
                StationDepartureResponse::fromStationDeparture
        ).toList();

        return new DepartureDetailsResponse(departure.getId(), TrainBasicDataResponse.fromTrain(departure.getTrain()),
                StationDepartureResponse.fromStationDeparture(startingStation), StationDepartureResponse.fromStationDeparture(destinationStation),
                allStations, totalNumOfSeats);
    }

}
