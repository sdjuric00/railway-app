package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.model.train.Station;
import com.ftn.railwayapp.response.train.StationResponse;

import java.util.List;

public interface IStationService {
    StationResponse createStation(String name, String coordinateX, String coordinateY);
    Station getStationById(String id) throws EntityNotFoundException;

    List<StationResponse> getAll();
}
