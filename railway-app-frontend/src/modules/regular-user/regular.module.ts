import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { MaterialModule } from "../material/material.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { SharedModule } from "../shared/shared.module";
import { RouterModule } from "@angular/router";
import { RegularRoutes } from "./regular.routes";
import { TicketBuyButtonComponent } from './components/ticket-buy-button/ticket-buy-button.component';

@NgModule({
  declarations: [
    TicketBuyButtonComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule.forChild(RegularRoutes),
  ],
  providers: [
  ],
  exports: [
    TicketBuyButtonComponent
  ]
})
export class RegularModule { }