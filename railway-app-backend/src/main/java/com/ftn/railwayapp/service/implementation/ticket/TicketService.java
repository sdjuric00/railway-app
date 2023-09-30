package com.ftn.railwayapp.service.implementation.ticket;

import com.ftn.railwayapp.repository.ticket.TicketRepository;
import com.ftn.railwayapp.service.interfaces.ITicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketService implements ITicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public int getNumOfSoldTicketsForDeparture(String departureId) {

        return this.ticketRepository.getNumOfSoldTicketsForDeparture(departureId).size();
    }
}
