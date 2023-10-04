import { Component, Input } from '@angular/core';
import { BalanceTrasaction } from 'src/modules/shared/model/balance-account';

@Component({
  selector: 'app-transaction-row',
  templateUrl: './transaction-row.component.html',
  styleUrls: ['./transaction-row.component.scss']
})
export class TransactionRowComponent {
  @Input() transaction: BalanceTrasaction
  @Input() index: number

}
