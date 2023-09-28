package com.ftn.railwayapp.service.implementation.train;

import com.ftn.railwayapp.model.train.Wagon;
import com.ftn.railwayapp.repository.train.WagonRepository;
import com.ftn.railwayapp.request.train.WagonRequest;
import com.ftn.railwayapp.service.interfaces.IWagonService;
import org.springframework.stereotype.Service;

@Service
public class WagonService implements IWagonService {

    private final WagonRepository wagonRepository;

    public WagonService(WagonRepository wagonRepository) {
        this.wagonRepository = wagonRepository;
    }

    @Override
    public Wagon saveWagon(WagonRequest wagonRequest) {
        return this.wagonRepository.save(new Wagon(
                wagonRequest.getWagonNum(),
                wagonRequest.getSeatsPerRow(),
                wagonRequest.getNumberOfRows(),
                wagonRequest.getTotalNumOfSeats(),
                wagonRequest.isSeatsWithTables(),
                wagonRequest.getSeatReservationPrice(),
                wagonRequest.isVipSection()
        ));
    }

}
