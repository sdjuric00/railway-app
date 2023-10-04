import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreatePayment, RedirectInfo } from 'src/modules/shared/model/payments';
import { ConfigService } from 'src/modules/shared/services/config/config.service';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor( private http: HttpClient, private configService: ConfigService) { }

  
  createPaymentRequest(balanceAccountId: number, numOfTokens: number): CreatePayment {

      return {balanceAccountId: balanceAccountId, numOfTokens: numOfTokens};
  }

  completePaymentRequest(balanceAccountId: number, numOfTokens: number, payerId: string, paymentId: string): CreatePayment {

      return {balanceAccountId: balanceAccountId, numOfTokens: numOfTokens, payerId: payerId, paymentId: paymentId};
  }

  createPayment(data: CreatePayment): Observable<RedirectInfo> {
      return this.http.post<RedirectInfo>(
        this.configService.CREATE_PAYMENT_URL,
        data
      );
    }

    completePayment(data: CreatePayment): Observable<boolean> {
      return this.http.post<boolean>(
        this.configService.COMPLETE_PAYMENT_URL,
        data
      );
    }

}
