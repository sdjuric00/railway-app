package com.ftn.railwayapp.model.train;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "departures")
public class Departure {

    @Id
    private String id;

    private LocalDateTime startTime;
    private int durationInMinutes;
    private int delayedMinutes;
    private boolean cancelled;
    private List<StationDeparture> stationDepartures = new ArrayList<>();

    @DBRef
    private Train train;

    @DBRef
    private List<OccupiedSeats> occupiedSeats = new ArrayList<>();

    public Departure(LocalDateTime startTime,
                     int durationInMinutes,
                     List<StationDeparture> stationDepartures,
                     Train train
    ) {
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
        this.delayedMinutes = 0;
        this.cancelled = false;
        this.stationDepartures = stationDepartures;
        this.train = train;
    }
}
