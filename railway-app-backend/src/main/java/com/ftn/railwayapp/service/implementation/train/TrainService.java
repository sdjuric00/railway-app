package com.ftn.railwayapp.service.implementation.train;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.model.enums.TrainBenefits;
import com.ftn.railwayapp.model.enums.TrainType;
import com.ftn.railwayapp.model.train.Train;
import com.ftn.railwayapp.model.train.Wagon;
import com.ftn.railwayapp.repository.train.TrainRepository;
import com.ftn.railwayapp.request.train.WagonRequest;
import com.ftn.railwayapp.response.train.TrainResponse;
import com.ftn.railwayapp.service.interfaces.ITrainService;
import com.ftn.railwayapp.service.interfaces.IWagonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.ftn.railwayapp.response.train.TrainResponse.fromTrain;

@Service
public class TrainService implements ITrainService {

    private final TrainRepository trainRepository;

    private final IWagonService wagonService;


    public TrainService(TrainRepository trainRepository, IWagonService wagonService) {
        this.trainRepository = trainRepository;
        this.wagonService = wagonService;
    }

    @Override
    public Train getTrainById(String id) throws EntityNotFoundException {
        return trainRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("train"));
    }

    @Override
    public TrainResponse createTrain(String name,
                                     TrainType type,
                                     List<WagonRequest> wagons,
                                     List<TrainBenefits> trainBenefits
    ) {
        List<Wagon> savedWagons = saveWagons(wagons);

        return fromTrain(trainRepository.save(
                new Train(name, type, savedWagons, trainBenefits)
        ));
    }

    @Override
    public List<Train> findTrainsByType(String trainType) {
        List<TrainType> trainTypes = parseToTrainType(trainType);

        return this.trainRepository.findTrainsByType(trainTypes);
    }

    private List<TrainType> parseToTrainType(String trainType) {
        List<TrainType> trainTypes = new ArrayList<>();
        switch (trainType.toUpperCase(Locale.ROOT)) {
            case "SOKO" -> trainTypes.add(TrainType.SOKO);
            case "REGIO" -> trainTypes.add(TrainType.REGIO);
            case "ALL" -> {
                trainTypes.add(TrainType.SOKO);
                trainTypes.add(TrainType.REGIO);
            }
        }

        return trainTypes;
    }

    private List<Wagon> saveWagons(List<WagonRequest> wagons) {

        return wagons.stream().map(this.wagonService::saveWagon)
                .collect(Collectors.toList());
    }
}
