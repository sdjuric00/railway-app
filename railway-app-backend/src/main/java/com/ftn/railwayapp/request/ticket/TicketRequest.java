package com.ftn.railwayapp.request.ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.ftn.railwayapp.util.ErrorMessages.WRONG_ID;
import static com.ftn.railwayapp.util.ErrorMessages.WRONG_PASSENGERS;

@Getter
@Setter
@AllArgsConstructor
public class TicketRequest {

    @NotNull(message = WRONG_ID)
    private Long userId;

    @NotBlank(message = WRONG_ID)
    private String departureId;

    @NotBlank(message = WRONG_ID)
    private String startStationId;

    @NotBlank(message = WRONG_ID)
    private String destinationStationId;

    @NotEmpty(message = WRONG_PASSENGERS)
    private List<String> passengers = new ArrayList<>();

}
