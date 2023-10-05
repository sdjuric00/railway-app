import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/modules/auth/services/auth/auth.service';
import { UserLoginResponse } from 'src/modules/shared/model/user';
import { WebsocketService } from '../../services/websocket/websocket.service';
import { BellNotification } from 'src/modules/shared/model/bell-notification';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit, OnDestroy {
  loggedUser: UserLoginResponse | null
  authSubscription: Subscription
  isAdmin: boolean

  bellNotificationsSubscription: Subscription
  bellNotifications: BellNotification[] = []
  numOfNotifications: number = 0

  constructor(private authService: AuthService, 
              private router: Router,
              private webSocketService: WebsocketService
  ) {
    this.isAdmin = false;
  }

  ngOnInit(): void {
    this.authSubscription = this.authService.getSubjectCurrentUser().subscribe(
      res => {
        if (res) {
          this.loggedUser = res
          this.isAdmin = res.role.roleName === 'ROLE_ADMIN'
          this.loadNotifications()
        }
      }
    )
  }

  loadNotifications(): void {
    this.bellNotificationsSubscription = this.webSocketService.getBellNotifications().subscribe(
      res => {
        if (res && res !== this.bellNotifications) {
          this.bellNotifications = res
          this.numOfNotifications += res.length
        }
      }
    )
  }

  setAllAsSeen(): void {
    this.numOfNotifications = 0
  }

  redirectToUserManagementPage(): void {
    this.router.navigate(['/railway-system/shared/user'])
  }

  logOut() {
    this.authService.logOut()
    this.router.navigate(['/railway-system/auth/login'])
    this.loggedUser = null
    this.isAdmin = false
    this.webSocketService.resetBell()
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe()
    }

    if (this.bellNotificationsSubscription) {
      this.bellNotificationsSubscription.unsubscribe()
    }
  }

}
