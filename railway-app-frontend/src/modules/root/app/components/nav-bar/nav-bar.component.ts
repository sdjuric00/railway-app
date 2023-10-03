import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/modules/auth/services/auth/auth.service';
import { UserLoginResponse } from 'src/modules/shared/model/user';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit, OnDestroy {
  loggedUser: UserLoginResponse | null;
  authSubscription: Subscription;
  isAdmin: boolean;

  constructor(private authService: AuthService, 
              private router: Router
  ) {
    this.isAdmin = false;
  }

  ngOnInit(): void {
    this.authSubscription = this.authService.getSubjectCurrentUser().subscribe(
      res => {
        if (res) {
          this.loggedUser = res
          this.isAdmin = res.role.roleName === 'ROLE_ADMIN'
        }
      }
    )
  }

  redirectToUserManagementPage(): void {
    this.router.navigate(['/railway-system/shared/user'])
  }

  logOut() {
    this.authService.logOut()
    this.router.navigate(['/railway-system/auth/login'])
    this.loggedUser = null
    this.isAdmin = false
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe()
    }
  }

}
