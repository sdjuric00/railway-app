import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { UserService } from '../../services/user/user.service';
import { AuthService } from 'src/modules/auth/services/auth/auth.service';
import { ChangePasswordRequest, UserDetailData, UserLoginResponse, UserUpdateRequest } from '../../model/user';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-user-profile-management-page',
  templateUrl: './user-profile-management-page.component.html',
  styleUrls: ['./user-profile-management-page.component.scss']
})
export class UserProfileManagementPageComponent implements OnInit, OnDestroy{

  authSubscription: Subscription
  userSubscription: Subscription
  userUpdateSubscription: Subscription

  userDetailData: UserDetailData
  loggedUser: UserLoginResponse

  constructor(private authService: AuthService,
              private userService: UserService,
              private toast: ToastrService
  ) {}

  ngOnInit(): void {
      this.loadLoggedUser()
      this.loadDetailUserData()
  }

  loadDetailUserData(): void {
    this.userSubscription = this.userService.getUserById(localStorage.getItem('user_id')).subscribe(
      res => {
        this.userDetailData = res
      }, error => {
        this.toast.error(error.error, 'Error happened!')
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

  update(userRequest: UserUpdateRequest): void {
    this.userUpdateSubscription = this.userService.updateUser(userRequest, this.userDetailData.id).subscribe(
      res => {
        if (res) {
          this.toast.success('Your profile is updated!', 'Success!')
        }
      }, error => {
        this.toast.error(error.error, 'Error happened!')
      }
    )
  }

  updatePasswords(passwordsRequest: ChangePasswordRequest): void {
    this.userUpdateSubscription = this.userService.updatePasswords(passwordsRequest, this.userDetailData.id).subscribe(
      res => {
        if (res) {
          this.toast.success('Your password is changed!', 'Success!')
        }
      },error => {
        this.toast.error(error.error, 'Error happened!')
      }
    )
  }

  ngOnDestroy(): void {
      if (this.authSubscription) {
        this.authSubscription.unsubscribe()
      }

      if (this.userSubscription) {
        this.userSubscription.unsubscribe()
      }

      if (this.userUpdateSubscription) {
        this.userUpdateSubscription.unsubscribe()
      }
  }

}
