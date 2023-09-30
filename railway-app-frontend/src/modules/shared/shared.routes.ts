import { Routes } from "@angular/router";
import { DeparturesTimetableComponent } from "./pages/departures-timetable/departures-timetable.component";


export const SharedRoutes: Routes = [
  {
    path: 'departures-timetable',
    pathMatch: 'full',
    component: DeparturesTimetableComponent
  }
]