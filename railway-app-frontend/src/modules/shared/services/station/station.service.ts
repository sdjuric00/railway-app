import { Injectable } from '@angular/core';
import { ConfigService } from '../config/config.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Station } from '../../model/station';

@Injectable({
  providedIn: 'root'
})
export class StationService {

  constructor(private configService: ConfigService, private http: HttpClient) { }

  getAll(): Observable<Station[]> {
    return this.http.get<Station[]>(
      this.configService.ALL_STATIONS_URL
    );
  }

}
