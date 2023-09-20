package com.ftn.railwayapp.model.train;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "stations")
public class Station {

    @Id
    private String id;

    private String name;
    private double coordinateX;
    private double coordinateY;

}
