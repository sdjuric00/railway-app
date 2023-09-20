package com.ftn.railwayapp.model.train;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
    private HashMap<String, Double> prices = new HashMap<>();   //key is a station id, values i price
    private HashMap<String, String> stations = new HashMap<>(); //key arrival time, values station id

    @DBRef
    private Train train;

    @DBRef
    private List<OccupiedSeats> occupiedSeats = new ArrayList<>();

}
