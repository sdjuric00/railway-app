package com.ftn.railwayapp.service.implementation.ticket;

import com.ftn.railwayapp.exception.*;
import com.ftn.railwayapp.model.ticket.Ticket;
import com.ftn.railwayapp.model.train.Departure;
import com.ftn.railwayapp.model.train.StationDeparture;
import com.ftn.railwayapp.model.user.RegularUser;
import com.ftn.railwayapp.model.user.User;
import com.ftn.railwayapp.repository.ticket.TicketRepository;
import com.ftn.railwayapp.service.implementation.email.EmailService;
import com.ftn.railwayapp.service.interfaces.*;
import com.ftn.railwayapp.util.Constants;
import com.ftn.railwayapp.util.PDFGenerator;
import com.ftn.railwayapp.util.QRCodeGenerator;
import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.ftn.railwayapp.util.Constants.*;

@Service
public class TicketService implements ITicketService {

    private final TicketRepository ticketRepository;

    private final IUserService userService;

    private final IDepartureService departureService;

    private final IBalanceAccountService balanceAccountService;

    private final EmailService emailService;

    public TicketService(TicketRepository ticketRepository,
                         IUserService userService,
                         IDepartureService departureService,
                         IBalanceAccountService balanceAccountService,
                         EmailService emailService
    ) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.departureService = departureService;
        this.balanceAccountService = balanceAccountService;
        this.emailService = emailService;
    }

    @Override
    public Integer getNumOfSoldTicketsForDeparture(String departureId) {

        return this.ticketRepository.getNumOfSoldTicketsForDeparture(departureId);
    }

    @Override
    public boolean createTicket(Long userId,
                                String departureId,
                                String startStationId,
                                String destinationStationId,
                                List<String> passengers
    ) throws EntityNotFoundException, OperationCannotBeCompletedException, QRCodeException, InvalidTimeException, IOException, MailCannotBeSentException {
        User regularUser = this.userService.getVerifiedRegularUserById(userId);
        Departure departure = this.departureService.getById(departureId);
        StationDeparture startingDeparture = this.departureService.getStationDepartureFromDeparture(departure, startStationId);
        StationDeparture destinationDeparture = this.departureService.getStationDepartureFromDeparture(departure, destinationStationId);
        int price = passengers.size() * (destinationDeparture.getPrice() - (startingDeparture.isStartingStation() ? 0 : destinationDeparture.getDiscountIfNotStarting()));

        departureService.checkDepartureInThePast(departure.getStartTime());
        departureService.checkDepartureIsCancelled(departure.isCancelled());
        departureService.checkStationsOrder(startingDeparture.getStationOrder(), destinationDeparture.getStationOrder());
        balanceAccountService.createTransaction(price, ((RegularUser)regularUser).getBalanceAccount().getId());

        Ticket ticket = createTicketWithoutQRCode(regularUser, departureId, startStationId, destinationStationId, passengers,price);

        QRCodeGenerator.createQrCode(Constants.getQRCodePath(ticket.getId().toString()), ticket.getId().toString());
        ticket.setQrCode(ticket.getId() + ".png");
        this.createPDF(departure, startingDeparture, destinationDeparture, price, ticket.getId(), passengers);
        this.emailService.sendTicketPurchaseMail(ticket.getId(), regularUser.getFullName(), PDFGenerator.readPDFAsBytes(PDF_OUT_FILE_PATH + ticket.getId() + ".pdf"));

        this.ticketRepository.save(ticket);

        return true;
    }

    private void createPDF(Departure departure,
                           StationDeparture startingDeparture,
                           StationDeparture destinationDeparture,
                           int price, Long ticketId,
                           List<String> passengers
    ) throws OperationCannotBeCompletedException {
        HashMap<String, Object> variables = new HashMap<>();
        String serbianDate = departure.getStartTime().getDayOfMonth() + "." + departure.getStartTime().getMonthValue() + "." +
                departure.getStartTime().getYear() + ".";

        variables.put("startingStation", startingDeparture.getStation().getName());
        variables.put("destinationStation", destinationDeparture.getStation().getName());
        variables.put("date", serbianDate);
        variables.put("leavingTime", startingDeparture.getLeavingTime());
        variables.put("arrivalTime", destinationDeparture.getLeavingTime());
        variables.put("price", price);
        variables.put("passengers", passengers);
        variables.put("srcQR", ticketId + ".png");
        variables.put("train", departure.getTrain());

        try {
            String html = PDFGenerator.parseThymeleafTemplate(variables, THYMLEAF_PDF_TEMPLATE_FILE_PATH);
            PDFGenerator.generatePdfFromHtml(html, PDF_OUT_FILE_PATH + ticketId + ".pdf");
        } catch (IOException | DocumentException e) {
            throw new OperationCannotBeCompletedException("Error occurred while pdf creation.");
        }
    }

    private Ticket createTicketWithoutQRCode(
            User regularUser,
            String departureId,
            String startStationId,
            String destinationStationId,
            List<String> passengers,
            int price
    ) {
        return this.ticketRepository.saveAndFlush(
                new Ticket((RegularUser) regularUser, departureId, startStationId, destinationStationId,
                        passengers, price)
        );
    }


}
