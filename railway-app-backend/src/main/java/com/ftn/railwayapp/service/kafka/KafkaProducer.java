package com.ftn.railwayapp.service.kafka;

import com.ftn.railwayapp.service.interfaces.IDepartureService;
import jakarta.annotation.PostConstruct;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, List<String>> kafkaTemplate;

    private final IDepartureService departureService;

    public KafkaProducer(KafkaTemplate<String, List<String>> kafkaTemplate, IDepartureService departureService) {
        this.kafkaTemplate = kafkaTemplate;
        this.departureService = departureService;
    }


    @PostConstruct
    public void sendDepartureIds() {
        List<String> departures = departureService.getDepartureIdsForFuture5Hours();
        kafkaTemplate.send("departure-ids", departures);
        System.out.println("Kafka sent a message: " + departures);
    }

}
