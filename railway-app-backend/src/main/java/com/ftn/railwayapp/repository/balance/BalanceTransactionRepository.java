package com.ftn.railwayapp.repository.balance;

import com.ftn.railwayapp.model.account.BalanceTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceTransactionRepository extends JpaRepository<BalanceTransaction, Long> {
}
