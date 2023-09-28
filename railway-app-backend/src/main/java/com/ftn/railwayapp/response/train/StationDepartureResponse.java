package com.ftn.railwayapp.response.train;

import com.ftn.railwayapp.model.train.StationDeparture;

import static com.ftn.railwayapp.response.train.StationResponse.fromStation;

public record StationDepartureResponse(StationResponse station, boolean startingStation,
                                       int price, int discountIfNotStarting, int stationOrder,
                                       String leavingTime) {

    public static StationDepartureResponse fromStationDeparture(StationDeparture stationDeparture) {

        return new StationDepartureResponse(fromStation(stationDeparture.getStation()), stationDeparture.isStartingStation(),
                stationDeparture.getPrice(), stationDeparture.getDiscountIfNotStarting(), stationDeparture.getStationOrder(),
                stationDeparture.getLeavingTime());
    }

}
