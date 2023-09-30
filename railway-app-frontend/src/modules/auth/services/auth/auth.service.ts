import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginResponse, UserLoginResponse } from 'src/modules/shared/model/user';
import { LoginRequest } from 'src/modules/shared/model/login-request';
import { ConfigService } from 'src/modules/shared/services/config/config.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public currentUser$: BehaviorSubject<UserLoginResponse | null> = new BehaviorSubject<UserLoginResponse | null>(null);

  constructor(private configService: ConfigService, private http: HttpClient) {
    this.currentUser$.next(null);
  }

  login(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(
      this.configService.LOGIN_URL,
      loginRequest
    );
  }

  onSocialLogin(tokenId: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(
      this.configService.SOCIAL_LOGIN_URL,
      tokenId
    );
  }

  setLocalStorage(loginResponse: LoginResponse): void {
    localStorage.setItem('token', loginResponse.token);
    localStorage.setItem('user', JSON.stringify(loginResponse.userLoginResponse));
    localStorage.setItem('user_id', loginResponse.userLoginResponse.id.toString());
    this.currentUser$.next(loginResponse.userLoginResponse);
  }

  logOut(): Observable<null> {
    this.currentUser$.next(null);
    return this.http.post<null>(
      this.configService.LOGOUT_URL,
      null
    );
  }

}
