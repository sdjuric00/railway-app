import { Routes } from "@angular/router";
import { TokenTransactionsPageComponent } from "./pages/token-transactions-page/token-transactions-page.component";
import { ProcessingPaymentComponent } from "./pages/processing-payment/processing-payment.component";
import { PaymentStatusComponent } from "./pages/payment-status/payment-status.component";

export const RegularRoutes: Routes = [
{
    path:'financial-card',
    pathMatch: "full",
    component: TokenTransactionsPageComponent,
    // canActivate: [RoleGuard],
    // data: {expectedRoles: "ROLE_REGULAR_USER"}
  },
  {
    path:'payment/process-payment/:balanceAccountId/:numOfTokens/process',
    pathMatch: "full",
    component: ProcessingPaymentComponent,
  },
  {
    path:'payment/status/:status',
    pathMatch: "full",
    component: PaymentStatusComponent,
  }
]