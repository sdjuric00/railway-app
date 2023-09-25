import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-verification-page',
  templateUrl: './verification-page.component.html',
  styleUrls: ['./verification-page.component.scss']
})
export class VerificationPageComponent implements OnInit, OnDestroy {
  firstDigit: string;
  secondDigit: string;
  thirdDigit: string;
  fourthDigit: string;
  verifyId: string | null;
  showForm = true;
  MAX_DIGIT_LENGTH = 4;

  constructor(private route: ActivatedRoute,
    private toast: ToastrService,
    private router: Router) {

  }

  ngOnInit(): void {
    this.verifyId = this.route.snapshot.paramMap.get('id');
  }

  ngOnDestroy(): void {
    throw new Error('Method not implemented.');
  }

  verify(): void {

  }

  sendCodeAgain(): void {

  }

}
