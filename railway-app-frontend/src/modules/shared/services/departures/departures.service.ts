import { Injectable } from '@angular/core';
import { ConfigService } from '../config/config.service';
import { HttpClient } from '@angular/common/http';
import { DepartureSearch, SearchRequest } from '../../model/search';
import { Observable } from 'rxjs';
import { DepartureDetails, DepartureRequest } from '../../model/departure';

@Injectable({
  providedIn: 'root'
})
export class DeparturesService {

  constructor(private configService: ConfigService, private http: HttpClient) { }

  searchDepartures(searchRequest: SearchRequest) {
    return this.http.get<DepartureSearch>(
      this.configService.getDepartureSearchURL(searchRequest)
    );
  }

  getDepartureDetails(departureId: string, startingStationId: string, destinationStationId: string): Observable<DepartureDetails> {
    return this.http.get<DepartureDetails>(
      this.configService.getDepartureDetailsURL(departureId, startingStationId, destinationStationId)
    );
  }

  createDeparture(departureRequest: DepartureRequest) {
    return this.http.post(
      this.configService.DEPARTURE_URL,
      departureRequest
    )
  }

}
