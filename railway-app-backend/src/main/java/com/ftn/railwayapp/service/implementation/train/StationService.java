package com.ftn.railwayapp.service.implementation.train;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.model.train.Station;
import com.ftn.railwayapp.repository.train.StationRepository;
import com.ftn.railwayapp.response.train.StationResponse;
import com.ftn.railwayapp.service.interfaces.IStationService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ftn.railwayapp.response.train.StationResponse.fromStation;

@Service
public class StationService implements IStationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public List<StationResponse> getAll() {
        List<Station> stations = this.stationRepository.findAll();

        return stations.stream().map(StationResponse::fromStation).toList();
    }

    @Override
    public StationResponse createStation(String name, String coordinateX, String coordinateY) {

        return fromStation(stationRepository.save(
                new Station(name, coordinateX, coordinateY)
        ));
    }

    @Override
    public Station getStationById(String id) throws EntityNotFoundException {

        return stationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("station"));
    }

}
