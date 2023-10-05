package com.ftn.railwayapp.service.kafka;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.service.interfaces.IDepartureService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class KafkaConsumer {

    private final IDepartureService departureService;

    public KafkaConsumer(IDepartureService departureService) {
        this.departureService = departureService;
    }

    @KafkaListener(topics = "updated-departure", groupId = "railway-system")
    public void consume(HashMap<String, String> updatedDeparture)
            throws EntityNotFoundException
    {
        System.out.println("Received updated departures: " + updatedDeparture);

        if (updatedDeparture.get("status").equalsIgnoreCase("delay")) {
            this.departureService.delayDeparture(updatedDeparture.get("id"));
        } else {
            this.departureService.cancelDeparture(updatedDeparture.get("id"));
        }
    }
}