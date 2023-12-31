import { Injectable } from '@angular/core';
import { enviroments } from 'src/enviroments/enviroments';
import { SearchRequest } from '../../model/search';


@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  constructor() { }

  API_URL = enviroments.apiUrl
  ////////////AUTH////////////////
  AUTH_URL = `${this.API_URL}/auth`
  LOGIN_URL = `${this.AUTH_URL}/login`
  SOCIAL_LOGIN_URL = `${this.AUTH_URL}/social-login`
  LOGOUT_URL = `${this.AUTH_URL}/logout`

  ///////////REGULAR_USER//////////
  REGULAR_USER_URL = `${this.API_URL}/regular-user`
  REGISTER_REGULAR_USER_URL = `${this.REGULAR_USER_URL}/register`
  ACTIVATE_ACCOUNT_URL = `${this.REGULAR_USER_URL}/activate-account`

  getBalanceAccountId(id: string | null): string {
    return `${this.REGULAR_USER_URL}/balance-account/${id}`
  }

  ///////////USER//////////
  USER_URL = `${this.API_URL}/user`

  getUserByIdURL(id: string | null): string {
    return `${this.USER_URL}/${id}`
  }

  updateUserByIdURL(id: number): string {
    return `${this.USER_URL}/${id}`
  }

  changePasswordURL(id: number): string {
    return `${this.USER_URL}/change-password/${id}`
  }

  ////////////VERIFY//////////////
  VERIFY_USER_URL = `${this.API_URL}/verify`
  SEND_CODE_AGAIN_URL = `${this.VERIFY_USER_URL}/send-code-again`


  ////////////STATION/////////////
  STATION_URL = `${this.API_URL}/station`
  ALL_STATIONS_URL = `${this.STATION_URL}/get-all`

  ////////////TRAIN/////////////
  TRAIN_URL = `${this.API_URL}/train`
  ALL_TRAINS_URL = `${this.TRAIN_URL}/all`

  ///////////DEPARTURE///////////
  DEPARTURE_URL = `${this.API_URL}/departure`

  getDepartureSearchURL(searchRequest: SearchRequest): string {
    return `${this.DEPARTURE_URL}/timetable?page=${searchRequest.page}`
    + `&pageSize=${searchRequest.pageSize}&trainType=${searchRequest.trainType}`
    + `&startingStation=${searchRequest.startingStationId}&destinationStation=${searchRequest.destinationStationId}`
    + `&time=${searchRequest.time}`
  }

  getDepartureDetailsURL(departureId: string, startingStationId: string, destinationStationId: string): string {
    return `${this.DEPARTURE_URL}/${departureId}/${startingStationId}/${destinationStationId}`
  }

  ///////////TICKET///////////
  TICKET_URL = `${this.API_URL}/ticket`

  getNumOfSoldTicketsURL(departureId: string): string {
    return `${this.TICKET_URL}/sold/${departureId}`
  }

  ///////////BALANCE ACCOUNT///////////
  BALANCE_ACCOUNT_URL = `${this.API_URL}/balance-account`

  ///////////////////PAYPAL///////////////////
  PAYPAL_URL = `${this.API_URL}/paypal`;
  CREATE_PAYMENT_URL = `${this.PAYPAL_URL}/create-payment`;
  COMPLETE_PAYMENT_URL = `${this.PAYPAL_URL}/complete-payment`;

}
