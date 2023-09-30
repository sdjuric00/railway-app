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
    private int numberOfRows;
    private int totalNumOfSeats;
    private boolean seatsWithTables;

    public Wagon(int wagonNum,
                 int seatsPerRow,
                 int numberOfRows,
                 int totalNumOfSeats,
                 boolean seatsWithTables
    ) {
        this.wagonNum = wagonNum;
        this.seatsPerRow = seatsPerRow;
        this.numberOfRows = numberOfRows;
        this.totalNumOfSeats = totalNumOfSeats;
        this.seatsWithTables = seatsWithTables;
    }
}
