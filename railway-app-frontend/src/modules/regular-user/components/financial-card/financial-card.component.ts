import { Component, Input } from '@angular/core';
import { BalanceAccount } from 'src/modules/shared/model/balance-account';
import { UserLoginResponse } from 'src/modules/shared/model/user';

@Component({
  selector: 'app-financial-card',
  templateUrl: './financial-card.component.html',
  styleUrls: ['./financial-card.component.scss']
})
export class FinancialCardComponent {
  @Input() balanceAccount: BalanceAccount
  @Input() loggedUser: UserLoginResponse

}
