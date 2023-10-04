import { Component, OnDestroy } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { BalanceAccountService } from '../../services/balance-account/balance-account.service';
import { Subscription } from 'rxjs';
import { RegularUserService } from 'src/modules/shared/services/regular-user/regular-user.service';
import { BalanceAccount } from 'src/modules/shared/model/balance-account';
import { UserLoginResponse } from 'src/modules/shared/model/user';
import { AuthService } from 'src/modules/auth/services/auth/auth.service';
import { PaymentService } from '../../services/payment.service';

@Component({
  selector: 'app-token-transactions-page',
  templateUrl: './token-transactions-page.component.html',
  styleUrls: ['./token-transactions-page.component.scss']
})
export class TokenTransactionsPageComponent implements OnDestroy {

  balanceAccSubscription: Subscription
  regularUserSubscription: Subscription
  authSubscription: Subscription
  paymentSubscription: Subscription

  balanceAccountId: number = -1
  balanceAccount: BalanceAccount

  loggedUser: UserLoginResponse

  constructor(private toast: ToastrService,
              private balanceAccountService: BalanceAccountService,
              private regularUserService: RegularUserService,
              private authService: AuthService,
              private paymentService: PaymentService
  ) {
    this.loadBalanceAccountId()
    this.loadLoggedUser()
  }

  buyTokens(numOfTokens: number): void {
    this.paymentSubscription = this.paymentService.createPayment(
        this.paymentService.createPaymentRequest(this.balanceAccountId, numOfTokens))
        .subscribe(
          res => {
            if (res.redirectUrl) {
              window.location.href = res.redirectUrl;
            }
          }, error => {
            this.toast.error(error.error, 'Payment creation failed!')
          }
      )
  }

  loadLoggedUser(): void {
    this.authSubscription = this.authService.getSubjectCurrentUser().subscribe(
      res => {
        if (res) {
          this.loggedUser = res
        }
      }
    )
  }

  loadBalanceAccountId(): void {
    this.regularUserSubscription = this.regularUserService.getBalanceAccountId(localStorage.getItem('user_id')).subscribe(
      res => {
        if (res) {
          this.balanceAccountId = res
          this.loadBalanceAccount()
        }
      }, error => {
        this.toast.error(error.error, 'Error happened!')
      }
    )
  }

  loadBalanceAccount(): void {
    this.balanceAccSubscription = this.balanceAccountService.getBalanceAccount(this.balanceAccountId).subscribe(
      res => {
        this.balanceAccount = res
        console.log(this.balanceAccount)
      }, error => {
        this.toast.error(error.error, 'Error happened!')
      }
    )
  }

  ngOnDestroy(): void {
      if (this.balanceAccSubscription) {
        this.balanceAccSubscription.unsubscribe()
      }

      if (this.regularUserSubscription) {
        this.regularUserSubscription.unsubscribe()
      }

      if (this.authSubscription) {
        this.authSubscription.unsubscribe()
      }

      if (this.paymentSubscription) {
        this.paymentSubscription.unsubscribe()
      }
  }

}
