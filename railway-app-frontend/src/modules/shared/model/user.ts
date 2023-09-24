
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