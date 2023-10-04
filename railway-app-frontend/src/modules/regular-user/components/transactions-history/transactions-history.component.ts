import { Component, Input } from '@angular/core';
import { BalanceTrasaction } from 'src/modules/shared/model/balance-account';

@Component({
  selector: 'app-transactions-history',
  templateUrl: './transactions-history.component.html',
  styleUrls: ['./transactions-history.component.scss']
})
export class TransactionsHistoryComponent {
  @Input() transactions: BalanceTrasaction[]

}
