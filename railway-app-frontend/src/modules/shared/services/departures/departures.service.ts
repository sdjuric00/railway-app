import { Injectable } from '@angular/core';
import { ConfigService } from '../config/config.service';
import { HttpClient } from '@angular/common/http';
import { DepartureSearch, SearchRequest } from '../../model/search';

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

}
