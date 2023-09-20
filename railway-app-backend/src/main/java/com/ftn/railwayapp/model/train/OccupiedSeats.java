package com.ftn.railwayapp.model.train;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "occupiedSeats")
public class OccupiedSeats {

    @Id
    private String id;

    private int seatNum;
    private int wagonNum;

}
