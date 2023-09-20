package com.ftn.railwayapp.model.train;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "wagons")
public class Wagon {

    @Id
    private String id;

    private int wagonNum;
    private int seatsPerRow;
    private int totalNumOfSeats;
    private boolean seatsWithTables;
    private boolean doubleSeats;
    private double seatReservationPrice;
    private boolean vipSection;
}
