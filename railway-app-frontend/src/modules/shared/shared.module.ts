import { NgModule } from "@angular/core";
import { MaterialModule } from "../material/material.module";
import { CommonModule } from "@angular/common";
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from "./interceptors/auth-interceptor";
import { DepartureFilteringComponent } from './components/departure-filtering/departure-filtering.component';
import { DeparturesTableComponent } from './components/departures-table/departures-table.component';
import { DepartureRowComponent } from './components/departure-row/departure-row.component';
import { DeparturesTimetableComponent } from './pages/departures-timetable/departures-timetable.component';
import { RouterModule } from "@angular/router";
import { SharedRoutes } from "./shared.routes";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { TrainBenefitsIconsComponent } from './components/train-benefits-icons/train-benefits-icons.component';


@NgModule({
  declarations: [  
    DepartureFilteringComponent,
     DeparturesTableComponent, 
     DepartureRowComponent, 
     DeparturesTimetableComponent, TrainBenefitsIconsComponent
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