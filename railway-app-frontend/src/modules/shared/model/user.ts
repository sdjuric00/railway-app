import { GenderOptions } from "./gender";

export interface Address {
    city: string,
    street: string,
    streetNumber: string,
    zipcode: string
}

export interface Role {
    id: number,
    roleName:string
}

export interface UserLoginResponse {
    id: number,
    email: string,
    role: Role
}

export interface LoginResponse {
    token: string;
    userLoginResponse: UserLoginResponse;
}

export const genderOptions: GenderOptions[] = [
    {value: 'MALE', viewValue: 'Male'},
    {value: 'FEMALE', viewValue: 'Female'},
    {value: 'NOT_PROVIDED', viewValue: 'Other/Not provided'},
  ];

export interface UserDetailData {
    id: number,
    email: string,
    fullName: string,
    gender: string,
    address: Address,
    role: Role,
    socialAccount: boolean
}

export interface UserUpdateRequest {
    email: string,
    fullName: string,
    gender: string,
    city: string,
    street: string,
    streetNumber: string,
    zipcode: string
}

export interface ChangePasswordRequest {
    oldPassword: string,
    newPassword: string,
    confirmPassword: string
}

