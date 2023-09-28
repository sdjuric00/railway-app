package com.ftn.railwayapp.repository.train;

import com.ftn.railwayapp.model.train.Wagon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WagonRepository extends MongoRepository<Wagon, String> {
}
