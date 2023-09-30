import { NgModule } from "@angular/core";
import { MaterialModule } from "../material/material.module";
import { CommonModule } from "@angular/common";
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from "./interceptors/auth-interceptor";
import { DepartureFilteringComponent } from './components/departure-filtering/departure-filtering.component';
import { DeparturesTableComponent } from './components/departures-table/departures-table.component';
import { DeparturesTimetableComponent } from './pages/departures-timetable/departures-timetable.component';
import { RouterModule } from "@angular/router";
import { SharedRoutes } from "./shared.routes";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { TrainBenefitsIconsComponent } from './components/train-benefits-icons/train-benefits-icons.component';
import { DepartureDetailsPageComponent } from './pages/departure-details-page/departure-details-page.component';
import { TrainDepartureDetailsComponent } from './components/train-departure-details/train-departure-details.component';
import { AllStationsTableComponent } from './components/all-stations-table/all-stations-table.component';


@NgModule({
  declarations: [  
    DepartureFilteringComponent,
     DeparturesTableComponent, 
     DeparturesTimetableComponent, TrainBenefitsIconsComponent, DepartureDetailsPageComponent, TrainDepartureDetailsComponent, AllStationsTableComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule.forChild(SharedRoutes)
  ],
  exports: [
  ],
  providers:[
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
  ]
})
export class SharedModule { }