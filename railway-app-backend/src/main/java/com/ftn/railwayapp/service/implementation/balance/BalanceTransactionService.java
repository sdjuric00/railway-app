package com.ftn.railwayapp.service.implementation.balance;

import com.ftn.railwayapp.repository.balance.BalanceTransactionRepository;
import com.ftn.railwayapp.service.interfaces.IBalanceTransactionService;

public class BalanceTransactionService implements IBalanceTransactionService {

    private final BalanceTransactionRepository balanceTransactionRepository;

    public BalanceTransactionService(BalanceTransactionRepository balanceTransactionRepository) {
        this.balanceTransactionRepository = balanceTransactionRepository;
    }
}
