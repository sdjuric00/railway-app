import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { VerificationService } from '../../services/verification/verification.service';
import { VerifyRequest } from 'src/modules/shared/model/verification';

@Component({
  selector: 'app-verification-page',
  templateUrl: './verification-page.component.html',
  styleUrls: ['./verification-page.component.scss']
})
export class VerificationPageComponent implements OnInit, OnDestroy {
  firstDigit: string
  secondDigit: string
  thirdDigit: string
  fourthDigit: string;
  verifyId: string | null
  showForm = true
  MAX_DIGIT_LENGTH = 4

  verificationSubscription: Subscription
  sendCodeAgainSubscription: Subscription

  constructor(private route: ActivatedRoute,
    private toast: ToastrService,
    private router: Router,
    private verificationService: VerificationService) {

  }

  ngOnInit(): void {
    this.verifyId = this.route.snapshot.paramMap.get('id');
  }

  containsOnlyNumbers(str: string) {
    return /^\d+$/.test(str);
  }

  checkValidationCode(): boolean {
    const securityCode: string =
      this.firstDigit + this.secondDigit + this.thirdDigit + this.fourthDigit;
    if (securityCode.length !== this.MAX_DIGIT_LENGTH) {
      this.toast.error('You need to add 4 digits.', 'Error');

      return false;
    } else if (!this.containsOnlyNumbers(securityCode)) {
      this.toast.error('You can input only digits!', 'Error');

      return false;
    } 
    
    return true;
  }

  verify(): void {
     if (this.checkValidationCode()) {
      const securityCode: string = this.firstDigit + this.secondDigit + this.thirdDigit + this.fourthDigit;
      const verifyRequest: VerifyRequest | null = this.verificationService.createVerifyRequest(this.verifyId, securityCode)

      this.verificationService.verify(verifyRequest).subscribe(
        res => {
          this.toast.success('Verification successful!', 'Success!')
          this.router.navigate(['/railway-system/auth/successfull-verification'])
        },
        err => {
          this.toast.error(err.error, 'Error happened!')
        }
      );
     }
  }

  sendCodeAgain(): void {
    this.sendCodeAgainSubscription = this.verificationService
      .sendCodeAgain(this.verifyId)
      .subscribe(
        res => (this.showForm = !this.showForm),
        error =>{
          this.toast.error('Email cannot be sent.', 'Error happened!');
        }
      );
  }

  ngOnDestroy(): void {
    if (this.verificationSubscription) {
      this.verificationSubscription.unsubscribe()
    }

    if (this.sendCodeAgainSubscription) {
      this.sendCodeAgainSubscription.unsubscribe()
    }
  }


}
