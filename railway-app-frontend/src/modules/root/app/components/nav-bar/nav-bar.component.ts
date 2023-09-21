import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/modules/auth/services/auth/auth.service';
import { UserSecurityResponse } from 'src/modules/shared/model/user';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit, OnDestroy {
  loggedUser: UserSecurityResponse;
  authSubscription: Subscription;
  isAdmin: boolean;

  constructor(private authService: AuthService, private router: Router) {
    this.isAdmin = false;
  }

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  ngOnDestroy(): void {
      if (this.authSubscription) {
        this.authSubscription.unsubscribe()
      }
  }

}
