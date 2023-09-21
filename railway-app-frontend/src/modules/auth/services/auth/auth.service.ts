import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginResponse, UserSecurityResponse } from 'src/modules/shared/model/user';
import { LoginRequest } from 'src/modules/shared/requests/login-request';
import { ConfigService } from 'src/modules/shared/services/config/config.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public currentUser$: BehaviorSubject<UserSecurityResponse | null> = new BehaviorSubject<UserSecurityResponse | null>(null);

  constructor(private configService: ConfigService, private http: HttpClient) {
    this.currentUser$.next(null);
  }

  login(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(
      this.configService.LOGIN_URL,
      loginRequest
    );
  }

  setLocalStorage(loginResponse: LoginResponse): void {
    localStorage.setItem('token', loginResponse.token);
    localStorage.setItem('user', JSON.stringify(loginResponse.userSecurityResponse));
    console.log(loginResponse)
    console.log(loginResponse.userSecurityResponse)
    localStorage.setItem('email', loginResponse.userSecurityResponse.email);
    this.currentUser$.next(loginResponse.userSecurityResponse);
  }

  logOut(): Observable<null> {
    this.currentUser$.next(null);
    return this.http.post<null>(
      this.configService.LOGOUT_URL,
      null
    );
  }

}
