import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { Subscription } from 'rxjs';
import { LoginRequest } from 'src/modules/shared/requests/login-request';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnDestroy {

  hide: boolean;

  authSubscription: Subscription;

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required ]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
  });

  constructor(private authService: AuthService, private router: Router,) {
    this.hide = true
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
            this.router.navigate(['/railway-system/shared/home']);
        },
        error => {
          // this.toast.error(error.error, 'Login failed');
        });}
      }

  }

  ngOnDestroy(): void {
      if (this.authSubscription) {
        this.authSubscription.unsubscribe();
      }
  }

}
