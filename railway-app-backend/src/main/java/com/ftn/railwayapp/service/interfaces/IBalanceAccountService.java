package com.ftn.railwayapp.service.interfaces;

import com.ftn.railwayapp.exception.EntityNotFoundException;
import com.ftn.railwayapp.exception.OperationCannotBeCompletedException;
import com.ftn.railwayapp.model.account.BalanceAccount;

public interface IBalanceAccountService {

    BalanceAccount createBalanceAccount();
    boolean createTransaction(int price, Long balanceAccountId) throws OperationCannotBeCompletedException, EntityNotFoundException;
    BalanceAccount getBalanceAccountById(Long id) throws EntityNotFoundException;
    boolean updateBalance(Long balanceAccountId, int numOfTokens, int tokenPrice, String currency) throws EntityNotFoundException;
}
