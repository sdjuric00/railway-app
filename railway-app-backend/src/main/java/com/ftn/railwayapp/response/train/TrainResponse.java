package com.ftn.railwayapp.response.train;

import com.ftn.railwayapp.model.enums.TrainBenefits;
import com.ftn.railwayapp.model.enums.TrainType;
import com.ftn.railwayapp.model.train.Train;
import com.ftn.railwayapp.model.train.Wagon;

import java.util.List;

public record TrainResponse(String id, String name, TrainType type, List<Wagon> wagons, List<TrainBenefits> trainBenefits) {

    public static TrainResponse fromTrain(Train train) {
        return new TrainResponse(train.getId(), train.getName(), train.getType(), train.getWagons(), train.getTrainBenefits());
    }
}
