package com.ftn.railwayapp.repository.train;

import com.ftn.railwayapp.model.train.Station;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StationRepository extends MongoRepository<Station, String> {
}
