import { Injectable } from '@angular/core';
import { enviroments } from 'src/enviroments/enviroments';


@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  constructor() { }

  API_URL = enviroments.apiUrl;
  ////////////AUTH////////////////
  AUTH_URL = `${this.API_URL}/auth`;
  LOGIN_URL = `${this.AUTH_URL}/login`;
  SOCIAL_LOGIN_URL = `${this.AUTH_URL}/social-login`;
  LOGOUT_URL = `${this.AUTH_URL}/logout`;

}
