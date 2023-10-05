import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { CompatClient, Stomp } from '@stomp/stompjs';
import { enviroments } from 'src/enviroments/enviroments';
import { BellNotification } from 'src/modules/shared/model/bell-notification';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private stompClient: CompatClient | null = null;
  initialized = false;

  bell$: BehaviorSubject<BellNotification[]>;

  constructor() {
    if (!this.stompClient) {
      this.initialized = false;
      this.connect();
    }

    this.bell$ = new BehaviorSubject<BellNotification[]>([]);
  }

  connect() {
    if (!this.initialized && localStorage.getItem('user_id') !== null) {
      this.initialized = true;
      const serverUrl = enviroments.webSocketUrl;
      const ws = new SockJS(serverUrl);
      this.stompClient = Stomp.over(ws);

      const that = this;

      if (this.stompClient) {
       this.stompClient.connect({}, function () {
        that.bellNotificationsUpdate();
       })
      }

    }
  }

  bellNotificationsUpdate() {
    this.stompClient?.subscribe(
      enviroments.subscriberUrl,
      (      message: { body: string; }) => {
        const bellNotification: BellNotification = JSON.parse(message.body);
        console.log(bellNotification)
        this.addNotification(bellNotification);
      }
    );
  }

  addNotification(bellNotification: BellNotification): void {
    const copyNotifications: BellNotification[] = this.bell$.value;
    this.bell$.next([bellNotification, ...copyNotifications]);
  }

  resetBell(): void {
    this.initialized = false
    this.stompClient = null
    this.bell$.next([])
  }

  emptyBell(): void {
    this.bell$.next([])
  }

  getBellNotifications(): BehaviorSubject<BellNotification[]> {
    
    return this.bell$
  }

  
}
