import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-total-price-check',
  templateUrl: './total-price-check.component.html',
  styleUrls: ['./total-price-check.component.scss']
})
export class TotalPriceCheckComponent {
  @Input() pricePerPerson: number
  @Input() numOfPeople: number
}
