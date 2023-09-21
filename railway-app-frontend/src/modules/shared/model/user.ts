
export interface Role {
    id: number,
    roleName:string
}

export interface UserSecurityResponse {
    id: number,
    email: string,
    role: Role
}

export interface LoginResponse {
    token: string;
    userSecurityResponse: UserSecurityResponse;
}