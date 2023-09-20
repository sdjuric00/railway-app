package com.ftn.railwayapp.model.train;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "departureCreators")
public class DepartureCreator {

    @Id
    private String id;

    private LocalDateTime startTime;
    private int durationInMinutes;
    private String trainId;
    private List<String> days = new ArrayList<>();
    private List<String> months = new ArrayList<>();
    private HashMap<String, String> stations = new HashMap<>(); //key is expected arrival time, values is stationId
    private HashMap<String, Double> prices = new HashMap<>(); //key is station Id, values is price

}
