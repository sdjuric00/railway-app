package com.ftn.railwayapp.controller.ticket;

import com.ftn.railwayapp.exception.*;
import com.ftn.railwayapp.request.ticket.TicketRequest;
import com.ftn.railwayapp.service.interfaces.ITicketService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_REGULAR')")
    public boolean createTicket(@RequestBody @Valid TicketRequest ticketRequest)
            throws OperationCannotBeCompletedException, EntityNotFoundException,
            QRCodeException, InvalidTimeException, IOException, MailCannotBeSentException
    {

        return this.ticketService.createTicket(
          ticketRequest.getUserId(),
          ticketRequest.getDepartureId(),
          ticketRequest.getStartStationId(),
          ticketRequest.getDestinationStationId(),
          ticketRequest.getPassengers()
        );
    }

}
