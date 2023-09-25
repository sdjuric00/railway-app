import { Component, OnDestroy } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { GenderOptions } from 'src/modules/shared/model/gender';

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
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{6,50}$')
      ]),
      confirmPassword: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{6,50}$')
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

  constructor() {
    this.hide = true
  }

  register() {
    //za sifre provera
    

  }

  ngOnDestroy(): void {
      
  }

}
