import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-payment-status',
  templateUrl: './payment-status.component.html',
  styleUrls: ['./payment-status.component.scss']
})
export class PaymentStatusComponent {
  
  showSuccess: boolean = false

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    const numIndicator: number | null = Number(this.activatedRoute.snapshot.paramMap.get('status'))
    if (numIndicator && numIndicator === 1) {
      this.showSuccess = true
    } else {
      this.showSuccess = false
    }
  }

  goToBalance(): void {
    this.router.navigate([`/railway-system/regular/financial-card`]);
  }
}
