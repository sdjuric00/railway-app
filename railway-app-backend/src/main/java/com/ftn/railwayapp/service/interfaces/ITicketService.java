package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.exception.*;

import java.io.IOException;
import java.util.List;

public interface ITicketService {
    Integer getNumOfSoldTicketsForDeparture(String departureId);

    boolean createTicket(Long userId, String departureId, String startStationId, String destinationStationId, List<String> passengers) throws EntityNotFoundException, OperationCannotBeCompletedException, QRCodeException, InvalidTimeException, IOException, MailCannotBeSentException;

}
