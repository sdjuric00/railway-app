import { Injectable } from '@angular/core';
import { RegularUserRegistrationRequest } from '../../model/regular-user';
import { ConfigService } from '../config/config.service';
import { HttpClient } from '@angular/common/http';
import { UserDetailData } from '../../model/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegularUserService {

  constructor(private configService: ConfigService, private http: HttpClient) { }

  getBalanceAccountId(userId: string | null): Observable<number> {
    return this.http.get<number>(
      this.configService.getBalanceAccountId(userId)
    )
  }

  register(request: RegularUserRegistrationRequest | null): Observable<UserDetailData> {
    return this.http.post<UserDetailData>(
      this.configService.REGISTER_REGULAR_USER_URL,
      request
    )
  }

}
