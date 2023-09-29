package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.model.enums.TrainBenefits;
import com.ftn.railwayapp.model.enums.TrainType;
import com.ftn.railwayapp.model.train.Train;
import com.ftn.railwayapp.request.train.WagonRequest;
import com.ftn.railwayapp.response.train.TrainResponse;

import java.util.List;

public interface ITrainService {
    TrainResponse createTrain(String name, TrainType type, List<WagonRequest> wagons, List<TrainBenefits> trainBenefits);
    Train getTrainById(String id) throws EntityNotFoundException;
    List<Train> findTrainsByTypeAndBenefits(String trainType, String benefits);
}
