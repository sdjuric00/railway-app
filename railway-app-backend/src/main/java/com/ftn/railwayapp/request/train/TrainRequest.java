package com.ftn.railwayapp.request.train;

import com.ftn.railwayapp.model.enums.TrainBenefits;
import com.ftn.railwayapp.model.enums.TrainType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.ftn.railwayapp.util.ErrorMessages.WRONG_TRAIN_NAME;
import static com.ftn.railwayapp.util.ErrorMessages.WRONG_TRAIN_TYPE;

@Getter
@Setter
@AllArgsConstructor
public class TrainRequest {

    @NotBlank(message = WRONG_TRAIN_NAME)
    @Size(min = 4, max = 20, message = WRONG_TRAIN_NAME)
    private final String name;

    @NotNull(message = WRONG_TRAIN_TYPE)
    private final TrainType type;

    @Valid
    private List<WagonRequest> wagons = new ArrayList<>();

    private List<TrainBenefits> trainBenefits = new ArrayList<>();

}
