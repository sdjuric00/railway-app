import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TicketRequest } from 'src/modules/shared/model/ticket';
import { ConfigService } from 'src/modules/shared/services/config/config.service';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  
  constructor(private configService: ConfigService, private http: HttpClient) { }

  createTicket(ticketRequest: TicketRequest): Observable<boolean> {
    return this.http.post<boolean>(
      this.configService.TICKET_URL,
      ticketRequest
    );
  }

  createTicketRequest(userId: string | null, departureId: string, startId: string, destinationId: string, passengers: string[]): TicketRequest {
    
    return {
      userId: (userId !== null) ? +userId : -1,
      departureId: departureId,
      startStationId: startId,
      destinationStationId: destinationId,
      passengers: passengers
    }
  }


}
