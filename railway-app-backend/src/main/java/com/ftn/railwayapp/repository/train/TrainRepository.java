package com.ftn.railwayapp.repository.train;

import com.ftn.railwayapp.model.enums.TrainType;
import com.ftn.railwayapp.model.train.Train;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRepository extends MongoRepository<Train, String> {

    @Query("{'type': {$in: ?0}, 'trainBenefits': {$in: ?1} }")
    List<Train> findTrainsByTypeAndBenefits(List<TrainType> types, List<String> benefits);

    @Query("{'type': {$in: ?0} }")
    List<Train> findTrainsByType(List<TrainType> types);
}
