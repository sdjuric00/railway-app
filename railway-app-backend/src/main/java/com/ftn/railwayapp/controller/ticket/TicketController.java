package com.ftn.railwayapp.controller.ticket;

import com.ftn.railwayapp.service.interfaces.ITicketService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket")
@Validated
public class TicketController {

    private final ITicketService ticketService;

    public TicketController(ITicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("sold/{departureId}")
    @ResponseStatus(HttpStatus.OK)
    public int getNumOfSoldTickets(@PathVariable @NotNull String departureId) {

        return this.ticketService.getNumOfSoldTicketsForDeparture(departureId);
    }

}
