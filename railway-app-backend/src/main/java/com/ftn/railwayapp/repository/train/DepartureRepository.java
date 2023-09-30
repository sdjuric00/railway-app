package com.ftn.railwayapp.repository.train;

import com.ftn.railwayapp.model.train.Departure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DepartureRepository extends MongoRepository<Departure, String> {

    @Query("{ 'startTime': { $gte: ?0, $lte: ?1 }, 'train': {$in: ?2}}")
    Page<Departure> filterDepartures(LocalDateTime startTime, LocalDateTime endTime, List<String> trainIDs, Pageable pageable);
}
