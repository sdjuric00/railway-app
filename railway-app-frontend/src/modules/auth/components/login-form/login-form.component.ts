import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { Subscription } from 'rxjs';
import { LoginRequest } from 'src/modules/shared/model/login-request';
import { Router } from '@angular/router';
import {
  MicrosoftLoginProvider,
  SocialAuthService,
  SocialUser,
} from '@abacritt/angularx-social-login';
import { ToastrService } from 'ngx-toastr';
import { WebsocketService } from 'src/modules/root/app/services/websocket/websocket.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit, OnDestroy {

  hide: boolean
  socialUser: SocialUser | null

  authSubscription: Subscription
  initialized: boolean

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required ]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
  });

  constructor(private authService: AuthService, 
              private router: Router,
              private socialAuthService: SocialAuthService,
              private toastr: ToastrService,
              private webSocketService: WebsocketService
  ) {
    this.hide = true
    this.socialUser = null
    this.initialized = false
  }

  ngOnInit(): void {
    this.socialAuthService.authState.subscribe((user) => {
      this.socialUser = user;
      if (this.socialUser) {this.onSocialLogin(this.socialUser.idToken);}
    });
  }

  logIn(): void {
    if (!this.loginForm.invalid) {
      const emailControl = this.loginForm.get('email')?.value;
      const passwordControl = this.loginForm.get('password')?.value;

      if (emailControl && passwordControl) {
        const loginRequest: LoginRequest = {
          email: emailControl,
          password: passwordControl,
        };

        this.authSubscription = this.authService.login(loginRequest).subscribe(
        userResponse => {
          this.authService.setLocalStorage(userResponse);
          this.webSocketService.connect()
          this.socialAuthService.signOut()
          this.router.navigate(['/railway-system/shared/departures-timetable'])
        },
        error => {
          this.toastr.error(error.error, 'Error happened.')
        });}
      }

  }

  onSocialLogin(tokenId: string): void {
    if (tokenId && !this.initialized) {
      this.authService.onSocialLogin(tokenId).subscribe(
        userResponse => {
          if (userResponse) {
            this.initialized = true
            this.authService.setLocalStorage(userResponse);
            this.webSocketService.connect()
            this.router.navigate(['/railway-system/shared/departures-timetable'])
          }
        },
        err => {
          this.toastr.error(err.error, 'Error happened.')
        }
      )
    }
  }

  signInWithMicrosoft(): void {
    this.socialAuthService.signIn(MicrosoftLoginProvider.PROVIDER_ID);
  }

  ngOnDestroy(): void {
      if (this.authSubscription) {
        this.authSubscription.unsubscribe();
      }

      if (this.socialUser) {
        this.socialAuthService.signOut()
        this.socialUser = null
        this.initialized = false
      }
  }

}
