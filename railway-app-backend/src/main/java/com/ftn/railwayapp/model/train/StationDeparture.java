package com.ftn.railwayapp.model.train;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StationDeparture {

    @DBRef
    private Station station;
    private boolean startingStation;
    private int price;
    private int discountIfNotStarting;
    private int stationOrder;
    private String leavingTime;
}
