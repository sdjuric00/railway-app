import { NgModule } from "@angular/core";
import { MaterialModule } from "../material/material.module";
import { CommonModule } from "@angular/common";
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from "./interceptors/auth-interceptor";
import { DepartureFilteringComponent } from './components/departure-filtering/departure-filtering.component';
import { DeparturesTableComponent } from './components/departures-table/departures-table.component';
import { DepartureRowComponent } from './components/departure-row/departure-row.component';
import { DeparturesTimetableComponent } from './pages/departures-timetable/departures-timetable.component';


@NgModule({
  declarations: [  
  
    DepartureFilteringComponent, DeparturesTableComponent, DepartureRowComponent, DeparturesTimetableComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    MaterialModule
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