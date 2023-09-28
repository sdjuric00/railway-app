package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.model.train.Wagon;
import com.ftn.railwayapp.request.train.WagonRequest;

public interface IWagonService {
    Wagon saveWagon(WagonRequest wagonRequest);
}
