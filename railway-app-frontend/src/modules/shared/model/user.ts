
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

export interface UserDetailData {
    id: number,
    email: string,
    fullName: string,
    gender: string,
    address: Address,
    role: Role,
    socialAccount: boolean
}

