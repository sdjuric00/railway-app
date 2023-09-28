package com.ftn.railwayapp.request.train;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.ftn.railwayapp.util.ErrorMessages.WRONG_BOOLEAN_VALUE_WAGON;
import static com.ftn.railwayapp.util.ErrorMessages.WRONG_NEGATIVE_NUMBER_TRAIN;

@Getter
@Setter
@AllArgsConstructor
public class WagonRequest {

    @Positive(message = WRONG_NEGATIVE_NUMBER_TRAIN)
    private final int wagonNum;

    @Positive(message = WRONG_NEGATIVE_NUMBER_TRAIN)
    private final int seatsPerRow;

    @Positive(message = WRONG_NEGATIVE_NUMBER_TRAIN)
    private final int numberOfRows;

    @NotNull(message = WRONG_BOOLEAN_VALUE_WAGON)
    private final boolean seatsWithTables;

    @Positive(message = WRONG_NEGATIVE_NUMBER_TRAIN)
    private final double seatReservationPrice;

    @NotNull(message = WRONG_BOOLEAN_VALUE_WAGON)
    private final boolean vipSection;

    public int getTotalNumOfSeats() {
        return this.numberOfRows * this.seatsPerRow;
    }

}
