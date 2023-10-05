import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TrainBasic } from 'src/modules/shared/model/train';
import { ConfigService } from 'src/modules/shared/services/config/config.service';

@Injectable({
  providedIn: 'root'
})
export class TrainService {

  constructor(private configService: ConfigService, private http: HttpClient) { }

  getAll(): Observable<TrainBasic[]> {
    return this.http.get<TrainBasic[]>(
      this.configService.ALL_TRAINS_URL
    );
  }
}
