import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { PaymentService } from '../../services/payment.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-processing-payment',
  templateUrl: './processing-payment.component.html',
  styleUrls: ['./processing-payment.component.scss']
})
export class ProcessingPaymentComponent implements OnInit, OnDestroy{

  payerId: string
  paymentId: string
  balanceAccountId: number | null
  numOfTokens: number | null

  showLoadingScreen: boolean
  paymentSubscription: Subscription

  constructor(
    private activatedRoute: ActivatedRoute,
    private paymentService: PaymentService,
    private toast: ToastrService,
    private route: Router
  ) {
    this.payerId = '';
    this.paymentId = '';
    this.showLoadingScreen = true;
  }

  ngOnInit(): void {
    this.numOfTokens = Number(this.activatedRoute.snapshot.paramMap.get('numOfTokens'))
    this.balanceAccountId = Number(this.activatedRoute.snapshot.paramMap.get('balanceAccountId'))
    console.log(this.balanceAccountId)

    this.activatedRoute.queryParams.subscribe(params => {
      this.payerId = params['PayerID'];
      this.paymentId = params['paymentId'];
    });

    if (this.checkValidityOfParams()) {
      this.completePayment();
    }
  }

  completePayment(): void {
    if (this.balanceAccountId && this.numOfTokens) {

      this.paymentSubscription = this.paymentService.completePayment(
        this.paymentService.completePaymentRequest(this.balanceAccountId, this.numOfTokens, this.payerId, this.paymentId))
        .subscribe(
          response => {
            this.showLoadingScreen = false;
            this.toast.success(
              'Transaction is successfully completed.',
              'Payment completed!!'
              );
            this.route.navigate(['/railway-system/regular/payment/status/1']);
            console.log(response);
          },
          error => {
            this.showLoadingScreen = false;
            this.toast.error(
              error.error,
              'Payment completion failed!'
              );
            this.route.navigate(['/railway-system/regular/payment/status/-1']);
          })
    }
  }

  checkValidityOfParams(): boolean {

    return this.checkPayPalArguments() && this.checkTokenBankArguments();
  }

  checkPayPalArguments(): boolean {

    return !!(this.payerId !== '' && this.payerId && this.paymentId !== '' && this.paymentId);
  }

  checkTokenBankArguments(): boolean {

    return !!(this.numOfTokens && this.balanceAccountId);
  }

  ngOnDestroy(): void {
      if (this.paymentSubscription) {
        this.paymentSubscription.unsubscribe()
      }
  }

}
