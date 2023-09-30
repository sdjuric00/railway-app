package com.ftn.railwayapp.repository.ticket;

import com.ftn.railwayapp.model.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("select t from Ticket t where t.departureId=?1")
    List<Ticket> getNumOfSoldTicketsForDeparture(String departureId);
}
