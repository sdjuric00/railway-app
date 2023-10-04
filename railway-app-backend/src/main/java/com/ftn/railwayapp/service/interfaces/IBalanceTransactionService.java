package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.model.account.BalanceAccount;
import com.ftn.railwayapp.model.account.BalanceTransaction;

public interface IBalanceTransactionService {
    BalanceTransaction createTransaction(int tokensNum, int moneySpent, String currency, BalanceAccount balanceAccount);
}
