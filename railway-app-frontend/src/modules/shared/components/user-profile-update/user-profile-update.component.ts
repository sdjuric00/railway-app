import { AfterViewInit, Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { UserDetailData, UserUpdateRequest } from '../../model/user';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { genderOptions } from '../../model/user';
import { GenderOptions } from '../../model/gender';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-user-profile-update',
  templateUrl: './user-profile-update.component.html',
  styleUrls: ['./user-profile-update.component.scss', './../../../auth/components/registration-form/registration-form.component.scss']
})
export class UserProfileUpdateComponent implements AfterViewInit {
  @Input() userDetailData: UserDetailData
  @Output() onUpdateEvent = new EventEmitter()

  genderOptions: GenderOptions[] = genderOptions

  userUpdateForm = new FormGroup({
      fullName: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.pattern('[a-zA-Z ]*'),
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

  constructor(private toast: ToastrService) {

  }

  ngAfterViewInit(): void {
      this.userUpdateForm.patchValue({
        fullName: this.userDetailData?.fullName,
        gender: this.userDetailData?.gender,
        city: this.userDetailData?.address.city,
        street: this.userDetailData?.address.street,
        streetNumber: this.userDetailData?.address.streetNumber,
        zipcode: this.userDetailData?.address.zipcode
      })
  }

  update(): void {
    const fullName = this.userUpdateForm.get("fullName")?.value
    const gender = this.userUpdateForm.get("gender")?.value
    const street = this.userUpdateForm.get("street")?.value
    const city = this.userUpdateForm.get("city")?.value
    const streetNumber = this.userUpdateForm.get("streetNumber")?.value
    const zipcode = this.userUpdateForm.get("zipcode")?.value

    if (fullName && gender && street && city && street && streetNumber && zipcode) {
      const userRequest: UserUpdateRequest = {
        email: this.userDetailData.email,
        fullName: fullName,
        gender: gender,
        city: city,
        street: street,
        streetNumber: streetNumber,
        zipcode: zipcode
      }

      this.onUpdateEvent.emit(userRequest)
    } else {
      this.toast.error('Form is invalid, check all fields!', 'Error happened!')
    }

  }

}
