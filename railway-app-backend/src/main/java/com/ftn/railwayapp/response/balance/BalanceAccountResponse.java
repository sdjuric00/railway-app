package com.ftn.railwayapp.response.balance;

import com.ftn.railwayapp.model.account.BalanceAccount;
import com.ftn.railwayapp.model.account.BalanceTransaction;

import java.util.List;

public record BalanceAccountResponse(Long id, String accountNum, int tokensNum, int totalTokenSpending,
                                     List<BalanceTransaction> transactions ) {

    public static BalanceAccountResponse fromBalanceAccount(BalanceAccount balanceAccount) {

        return new BalanceAccountResponse(balanceAccount.getId(), balanceAccount.getAccountNum(), balanceAccount.getTokensNum(),
                balanceAccount.getTotalTokenSpending(), balanceAccount.getTransactions());
    }

}
