package com.ftn.railwayapp.service.implementation.balance;

import com.ftn.railwayapp.model.account.BalanceAccount;
import com.ftn.railwayapp.model.account.BalanceTransaction;
import com.ftn.railwayapp.repository.balance.BalanceTransactionRepository;
import com.ftn.railwayapp.service.interfaces.IBalanceTransactionService;
import org.springframework.stereotype.Service;

@Service
public class BalanceTransactionService implements IBalanceTransactionService {

    private final BalanceTransactionRepository balanceTransactionRepository;

    public BalanceTransactionService(BalanceTransactionRepository balanceTransactionRepository) {
        this.balanceTransactionRepository = balanceTransactionRepository;
    }

    public BalanceTransaction createTransaction(int tokensNum,
                                         int moneySpent,
                                         String currency,
                                         BalanceAccount balanceAccount
    ) {

        return this.balanceTransactionRepository.saveAndFlush(
                new BalanceTransaction(tokensNum, moneySpent, currency, balanceAccount)
        );
    }
}
