package com.ftn.railwayapp.response.train;

import com.ftn.railwayapp.model.enums.TrainBenefits;
import com.ftn.railwayapp.model.enums.TrainType;
import com.ftn.railwayapp.model.train.Train;

import java.util.List;


public record TrainBasicDataResponse(String id, String name, TrainType type, List<TrainBenefits> trainBenefits) {

    public static TrainBasicDataResponse fromTrain(Train train) {
        return new TrainBasicDataResponse(train.getId(), train.getName(), train.getType(), train.getTrainBenefits());
    }
}

