import { Injectable } from '@angular/core';
import { ConfigService } from '../config/config.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  constructor(private configService: ConfigService, private http: HttpClient) { }

  getNumOfSoldTickets(departureId: string): Observable<number> {
    return this.http.get<number>(
      this.configService.getNumOfSoldTicketsURL(departureId)
    );
  }

}
