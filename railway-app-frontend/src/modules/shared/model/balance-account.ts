
export interface BalanceTrasaction {
    id: number,
    timeStamp: string,
    tokensNum: number,
    moneySpent: number,
    currency: string
}

export interface BalanceAccount {
    id: number,
    accountNum: string,
    tokensNum: number,
    totalTokenSpending: number,
    transactions: BalanceTrasaction[]
}