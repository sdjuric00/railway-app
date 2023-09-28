package com.ftn.railwayapp.response.train;

import com.ftn.railwayapp.model.train.Station;

public record StationResponse(String id, String name, String coordinateX, String coordinateY) {

    public static StationResponse fromStation(Station station) {
        return new StationResponse(station.getId(), station.getName(), station.getCoordinateX(), station.getCoordinateY());
    }
}
