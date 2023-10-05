import { NgModule } from "@angular/core";
import { AdminRoutes } from "./admin.routes";
import { CommonModule } from "@angular/common";
import { MaterialModule } from "../material/material.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { SharedModule } from "../shared/shared.module";
import { RouterModule } from "@angular/router";
import { DepartureCreationPageComponent } from './pages/departure-creation-page/departure-creation-page.component';
import { StationDeparturesFormComponent } from './components/station-departures-form/station-departures-form.component';
import { AddedStationDeparturesDialogComponent } from './components/added-station-departures-dialog/added-station-departures-dialog.component';



@NgModule({
  declarations: [
  
    DepartureCreationPageComponent,
       StationDeparturesFormComponent,
       AddedStationDeparturesDialogComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    SharedModule,
    RouterModule.forChild(AdminRoutes),
  ],
  providers: [
  ],
})
export class AdminModule { }