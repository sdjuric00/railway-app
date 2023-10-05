import { Routes } from "@angular/router";
import { DepartureCreationPageComponent } from "./pages/departure-creation-page/departure-creation-page.component";


export const AdminRoutes: Routes = [
  {
    path: 'create-departure',
    pathMatch: 'full',
    component: DepartureCreationPageComponent
  }
]