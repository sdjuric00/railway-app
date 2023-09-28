package com.ftn.railwayapp.repository.train;

import com.ftn.railwayapp.model.train.Train;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends MongoRepository<Train, String> {
}
