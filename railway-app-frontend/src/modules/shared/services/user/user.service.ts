import { Injectable } from '@angular/core';
import { ConfigService } from '../config/config.service';
import { HttpClient } from '@angular/common/http';
import { ChangePasswordRequest, UserDetailData, UserUpdateRequest } from '../../model/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private configService: ConfigService, private http: HttpClient) { }

  getUserById(id: string | null): Observable<UserDetailData> {
    return this.http.get<UserDetailData>(
      this.configService.getUserByIdURL(id)
    );
  }

  updateUser(userRequest: UserUpdateRequest, id: number): Observable<UserDetailData> {
    return this.http.put<UserDetailData>(
      this.configService.updateUserByIdURL(id),
      userRequest
    );
  }

  updatePasswords(passwordsRequest: ChangePasswordRequest, id: number): Observable<boolean> {
    return this.http.put<boolean>(
      this.configService.changePasswordURL(id),
      passwordsRequest
    );
  }


}
