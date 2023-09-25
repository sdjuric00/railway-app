import { Routes } from "@angular/router";
import { WelcomePageComponent } from "./pages/welcome-page/welcome-page.component";
import { SuccessfulVerificationPageComponent } from "./pages/successful-verification-page/successful-verification-page.component";
import { VerificationPageComponent } from "./pages/verification-page/verification-page.component";

export const AuthRoutes: Routes = [
  {
    path: 'register',
    pathMatch: 'full',
    component: WelcomePageComponent
  },
  {
    path: "login",
    pathMatch: "full",
    component: WelcomePageComponent
  },
  {
    path: "successful-verification",
    pathMatch: "full",
    component: SuccessfulVerificationPageComponent
  },
  {
    path: "verify/:id",
    pathMatch: "full",
    component: VerificationPageComponent
  },
]