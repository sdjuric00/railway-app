package com.ftn.railwayapp.response.train;

import com.ftn.railwayapp.model.enums.TrainBenefits;
import com.ftn.railwayapp.model.train.Departure;
import com.ftn.railwayapp.model.train.StationDeparture;

import java.time.LocalDateTime;
import java.util.List;

public record DepartureSearchResponse(String id, String trainName, LocalDateTime startTime,
                                      String leavingTime, String arrivalTime, int delayed,
                                      boolean cancelled, List<TrainBenefits> trainBenefits,
                                      String startingStationId, String destinationStationId) {


    public static DepartureSearchResponse fromDeparture(Departure departure, String startingStationId, String destinationStationId) {
        String leavingTime = "", arrivalTime = "";
        for (StationDeparture stationDeparture : departure.getStationDepartures()) {
            if (stationDeparture.getStation().getId().equals(startingStationId)) {
                leavingTime = stationDeparture.getLeavingTime();
            } else if (stationDeparture.getStation().getId().equals(destinationStationId)) {
                arrivalTime = stationDeparture.getLeavingTime();
            }
        }

        return new DepartureSearchResponse(departure.getId(), departure.getTrain().getName(), departure.getStartTime(),
                leavingTime, arrivalTime, departure.getDelayedMinutes(), departure.isCancelled(),
                departure.getTrain().getTrainBenefits(), startingStationId, destinationStationId);
    }

}
