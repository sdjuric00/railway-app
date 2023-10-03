package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.InvalidTimeException;
import com.ftn.railwayapp.exception.OperationCannotBeCompletedException;
import com.ftn.railwayapp.exception.QRCodeException;

import java.util.List;

public interface ITicketService {
    int getNumOfSoldTicketsForDeparture(String departureId);

    boolean createTicket(Long userId, String departureId, String startStationId, String destinationStationId, List<String> passengers) throws EntityNotFoundException, OperationCannotBeCompletedException, QRCodeException, InvalidTimeException;

}
