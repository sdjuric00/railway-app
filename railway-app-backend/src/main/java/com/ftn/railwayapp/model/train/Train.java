package com.ftn.railwayapp.model.train;

import com.ftn.railwayapp.model.enums.TrainBenefits;
import com.ftn.railwayapp.model.enums.TrainType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "trains")
public class Train {

    @Id
    private String id;

    private String name;
    private TrainType type;

    @DBRef
    private List<Wagon> wagons = new ArrayList<>();

    private List<TrainBenefits> trainBenefits = new ArrayList<>();

    public Train(String name, TrainType type, List<Wagon> wagons, List<TrainBenefits> trainBenefits) {
        this.name = name;
        this.type = type;
        this.wagons = wagons;
        this.trainBenefits = trainBenefits;
    }
}
