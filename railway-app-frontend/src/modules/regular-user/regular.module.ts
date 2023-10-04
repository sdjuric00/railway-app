import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { MaterialModule } from "../material/material.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { RegularRoutes } from "./regular.routes";
import { TicketBuyButtonComponent } from './components/ticket-buy-button/ticket-buy-button.component';
import { BuyTicketDialogComponent } from './components/buy-ticket-dialog/buy-ticket-dialog.component';
import { PaypalBuyingFormComponent } from './components/paypal-buying-form/paypal-buying-form.component';
import { TransactionsHistoryComponent } from './components/transactions-history/transactions-history.component';
import { TokenTransactionsPageComponent } from './pages/token-transactions-page/token-transactions-page.component';
import { AddingPeopleInputsComponent } from './components/adding-people-inputs/adding-people-inputs.component';
import { TotalPriceCheckComponent } from './components/total-price-check/total-price-check.component';
import { PaymentStatusComponent } from './pages/payment-status/payment-status.component';
import { ProcessingPaymentComponent } from './pages/processing-payment/processing-payment.component';
import { FinancialCardComponent } from './components/financial-card/financial-card.component';
import { TransactionRowComponent } from './components/transaction-row/transaction-row.component';

@NgModule({
  declarations: [
    TicketBuyButtonComponent,
    BuyTicketDialogComponent,
    PaypalBuyingFormComponent,
    TransactionsHistoryComponent,
    TokenTransactionsPageComponent,
    AddingPeopleInputsComponent,
    TotalPriceCheckComponent,
    PaymentStatusComponent,
    ProcessingPaymentComponent,
    FinancialCardComponent,
    TransactionRowComponent
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