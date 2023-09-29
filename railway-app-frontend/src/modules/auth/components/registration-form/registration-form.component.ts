import { Component, OnDestroy } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { GenderOptions } from 'src/modules/shared/model/gender';
import { RegularUserRegistrationRequest } from 'src/modules/shared/model/regular-user';
import { RegularUserService } from 'src/modules/shared/services/regular-user/regular-user.service';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.scss']
})
export class RegistrationFormComponent implements OnDestroy {

  registrationForm = new FormGroup({
      email: new FormControl('', [
        Validators.required,
        Validators.email,
      ]),
      fullName: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.pattern('[a-zA-Z ]*'),
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
        Validators.pattern('^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{6,50}$')
      ]),
      confirmPassword: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
        Validators.pattern('^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{6,50}$')
      ]),
      gender: new FormControl('', [
        Validators.required
      ]),
      city: new FormControl('', [
        Validators.required,
        Validators.pattern('[a-zA-Z ]*'),
      ]),
      street: new FormControl('', [
        Validators.required,
        Validators.pattern('[a-zA-Z ]*'),
      ]),
      streetNumber: new FormControl('', [
        Validators.required,
        Validators.pattern('[a-zA-Z1-9 ]*'),
      ]),
      zipcode: new FormControl('', [
        Validators.required,
        Validators.pattern('[1-9][0-9]{4}'),
      ]),
  });

  genderOptions: GenderOptions[] = [
    {value: 'MALE', viewValue: 'Male'},
    {value: 'FEMALE', viewValue: 'Female'},
    {value: 'NOT_PROVIDED', viewValue: 'Other/Not provided'},
  ];

  hide: boolean
  regularUserSubscription: Subscription;

  constructor(private toastr: ToastrService,
              private regularUserService: RegularUserService,
              private router: Router
  ) {
    this.hide = true
  }

  isPasswordsMismatch(): boolean {
    return this.registrationForm.get('password')?.value !== this.registrationForm.get('confirmPassword')?.value
  }

 createRegistrationRequest(): RegularUserRegistrationRequest | null {
  const {
    email,
    password,
    confirmPassword,
    fullName,
    gender,
    city,
    street,
    streetNumber,
    zipcode
  } = this.registrationForm.controls;

  const values = {
    email: email?.value,
    password: password?.value,
    confirmPassword: confirmPassword?.value,
    fullName: fullName?.value,
    gender: gender?.value,
    city: city?.value,
    street: street?.value,
    streetNumber: streetNumber?.value,
    zipcode: zipcode?.value
  };

  if (Object.values(values).every(val => val !== undefined && val !== '')) {
    return values as RegularUserRegistrationRequest;
  }

  return null;
}


  register() {
    if (this.isPasswordsMismatch()) {
      this.toastr.error('Password and confirm password are not the same!', 'Password mismatch')
    } else if (this.registrationForm.invalid) {
      this.toastr.error('You made a mistake in form, check all fields.', 'Error happened')
    } else {
      const request: RegularUserRegistrationRequest | null = this.createRegistrationRequest()
      this.regularUserSubscription = this.regularUserService.register(request).subscribe(
        res => {
          this.toastr.success('Registration is successful, check your email.', 'Success!')
          this.router.navigate(['/railway-system/auth/login'])
        },
        err => {
          this.toastr.error(err.error, 'Error happened!')
        }
      );
    }
  }

  ngOnDestroy(): void {
      if (this.regularUserSubscription) {
        this.regularUserSubscription.unsubscribe()
      }
  }

}
