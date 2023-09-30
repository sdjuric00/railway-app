package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.InvalidDepartureDataException;
import com.ftn.railwayapp.exception.InvalidTimeException;
import com.ftn.railwayapp.exception.OperationCannotBeCompletedException;
import com.ftn.railwayapp.request.train.StationDepartureRequest;
import com.ftn.railwayapp.response.train.DepartureResponse;
import com.ftn.railwayapp.response.train.DepartureSearchResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface IDepartureService {
    DepartureResponse createDeparture(LocalDateTime startTime, int durationInMinutes,
                                      List<StationDepartureRequest> stationDepartures, String trainId)
            throws InvalidTimeException, EntityNotFoundException, InvalidDepartureDataException;

    Page<DepartureSearchResponse> departuresTimetable(int page, int pageSize, String trainType, String startingStationId, String destinationStationId, LocalDateTime time) throws OperationCannotBeCompletedException;
}
