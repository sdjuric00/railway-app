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

  ///////////REGULAR_USER//////////
  REGULAR_USER_URL = `${this.API_URL}/regular-user`;
  REGISTER_REGULAR_USER_URL = `${this.REGULAR_USER_URL}/register`;
  ACTIVATE_ACCOUNT_URL = `${this.REGULAR_USER_URL}/activate-account`

  ////////////VERIFY//////////////
  VERIFY_USER_URL = `${this.API_URL}/verify`;
  SEND_CODE_AGAIN_URL = `${this.VERIFY_USER_URL}/send-code-again`;


}
