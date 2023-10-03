import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ChangePasswordRequest } from '../../model/user';

@Component({
  selector: 'app-user-password-update',
  templateUrl: './user-password-update.component.html',
  styleUrls: ['./user-password-update.component.scss', './../../../auth/components/registration-form/registration-form.component.scss']
})
export class UserPasswordUpdateComponent {
  @Output() onPasswordChange = new EventEmitter() 

  hide: boolean = true
  passwordsForm = new FormGroup({
      oldPassword: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
        Validators.pattern('^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{6,50}$')
      ]),
      newPassword: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
        Validators.pattern('^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{6,50}$')
      ]),
      confirmPassword: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
        Validators.pattern('^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{6,50}$')
      ]),
  })

  constructor(private toast: ToastrService) {}

  update(): void {
    const currentPassword = this.passwordsForm.get('oldPassword')?.value
    const newPassword = this.passwordsForm.get('newPassword')?.value
    const confirmPassword = this.passwordsForm.get('confirmPassword')?.value

    if (currentPassword && newPassword && confirmPassword) {
      const passwordsRequest: ChangePasswordRequest = {
        oldPassword: currentPassword,
        newPassword: newPassword,
        confirmPassword: confirmPassword
      }

      this.onPasswordChange.emit(passwordsRequest)
    } else {
      this.toast.error('Form is invalid, check all fields!', 'Error happened!')
    }

  }

}
