import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { Subscription } from 'rxjs';
import { LoginRequest } from 'src/modules/shared/model/login-request';
import { Router } from '@angular/router';
import {
  GoogleLoginProvider,
  MicrosoftLoginProvider,
  SocialAuthService,
  SocialUser,
} from '@abacritt/angularx-social-login';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit, OnDestroy {

  hide: boolean
  socialUser: SocialUser | null

  authSubscription: Subscription

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required ]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
  });

  constructor(private authService: AuthService, 
              private router: Router,
              private socialAuthService: SocialAuthService,
              private toastr: ToastrService
  ) {
    this.hide = true
    this.socialUser = null
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
          //TODO
          // this.router.navigate(['/railway-system/shared/home']);
        },
        error => {
          this.toastr.error(error.error, 'Error happened.')
        });}
      }

  }

  onSocialLogin(tokenId: string): void {
    this.authService.onSocialLogin(tokenId).subscribe(
      userResponse => {
        this.authService.setLocalStorage(userResponse);
        console.log(userResponse);
      },
      err => {
        this.toastr.error(err.error, 'Error happened.')
      }
    )
  }

  signInWithMicrosoft(): void {
    this.socialAuthService.signIn(MicrosoftLoginProvider.PROVIDER_ID);
  }

  ngOnDestroy(): void {
      if (this.authSubscription) {
        this.authSubscription.unsubscribe();
      }

      if (this.socialUser) {
        this.socialAuthService.refreshAccessToken(GoogleLoginProvider.PROVIDER_ID);
        this.socialAuthService.refreshAccessToken(MicrosoftLoginProvider.PROVIDER_ID);
      }
  }

}
