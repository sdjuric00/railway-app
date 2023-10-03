import { Routes } from "@angular/router";
import { DeparturesTimetableComponent } from "./pages/departures-timetable/departures-timetable.component";
import { DepartureDetailsPageComponent } from "./pages/departure-details-page/departure-details-page.component";
import { UserProfileManagementPageComponent } from "./pages/user-profile-management-page/user-profile-management-page.component";


export const SharedRoutes: Routes = [
  {
    path: 'departures-timetable',
    pathMatch: 'full',
    component: DeparturesTimetableComponent
  },
  {
    path: 'departure-details/:departureId/:startingStationId/:destinationStationId',
    pathMatch: 'full',
    component: DepartureDetailsPageComponent
  },
  {
    path: 'user',
    pathMatch: 'full',
    component: UserProfileManagementPageComponent
  }
]