import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-train-benefits-icons',
  templateUrl: './train-benefits-icons.component.html',
  styleUrls: ['./train-benefits-icons.component.scss']
})
export class TrainBenefitsIconsComponent implements OnInit {
  @Input() benefits: string[] = []

  ngOnInit(): void {
      console.log(this.benefits)
  }
}
