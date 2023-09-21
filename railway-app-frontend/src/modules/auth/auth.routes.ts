import { Routes } from "@angular/router";
import { WelcomePageComponent } from "./pages/welcome-page/welcome-page.component";

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
]