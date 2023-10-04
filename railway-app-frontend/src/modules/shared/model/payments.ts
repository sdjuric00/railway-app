export interface CreatePayment {
    balanceAccountId: number;
    numOfTokens: number;
    payerId?: string;
    paymentId?: string;
}

export interface RedirectInfo {
    status?: string,
    redirectUrl?: string;
}