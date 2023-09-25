import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MaterialModule } from "../material/material.module";
import { RouterModule } from "@angular/router";
import { AuthRoutes } from "./auth.routes";
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { SharedModule } from "../shared/shared.module";
import { WelcomePageComponent } from './pages/welcome-page/welcome-page.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { RegistrationFormComponent } from './components/registration-form/registration-form.component';
import { GoogleSigninButtonModule } from "@abacritt/angularx-social-login";
import { VerificationPageComponent } from './pages/verification-page/verification-page.component';
import { SuccessfulVerificationPageComponent } from './pages/successful-verification-page/successful-verification-page.component';


@NgModule({
  declarations: [
      WelcomePageComponent,
      LoginFormComponent,
      RegistrationFormComponent,
      VerificationPageComponent,
      SuccessfulVerificationPageComponent,
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    SharedModule,
    RouterModule.forChild(AuthRoutes),
    GoogleSigninButtonModule
  ],
  providers: [
  ],
})
export class AuthModule { }