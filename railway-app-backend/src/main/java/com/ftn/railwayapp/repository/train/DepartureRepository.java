package com.ftn.railwayapp.repository.train;

import com.ftn.railwayapp.model.train.Departure;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartureRepository extends MongoRepository<Departure, String> {
}
