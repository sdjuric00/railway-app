package com.ftn.railwayapp.repository.ticket;

import com.ftn.railwayapp.model.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT COALESCE(SUM(t.numOfSeatsTaken), 0) FROM Ticket t WHERE t.departureId = ?1")
    Integer getNumOfSoldTicketsForDeparture(String departureId);
}
