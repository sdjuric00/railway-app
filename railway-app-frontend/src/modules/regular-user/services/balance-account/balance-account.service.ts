import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BalanceAccount } from 'src/modules/shared/model/balance-account';
import { ConfigService } from 'src/modules/shared/services/config/config.service';

@Injectable({
  providedIn: 'root'
})
export class BalanceAccountService {

  constructor(private configService: ConfigService, private http: HttpClient) { }

  getBalanceAccount(id: number): Observable<BalanceAccount> {
    return this.http.get<BalanceAccount>(
      `${this.configService.BALANCE_ACCOUNT_URL}/${id}`
    )
  }
}
