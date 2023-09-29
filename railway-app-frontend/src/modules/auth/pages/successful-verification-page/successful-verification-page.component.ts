import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-successful-verification-page',
  templateUrl: './successful-verification-page.component.html',
  styleUrls: ['./successful-verification-page.component.scss']
})
export class SuccessfulVerificationPageComponent {

   constructor(private router: Router) { }

  redirectToLogin() {
    this.router.navigate(['/railway-system/auth/login'])
  }

}
