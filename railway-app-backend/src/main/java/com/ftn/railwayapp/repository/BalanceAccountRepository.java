package com.ftn.railwayapp.repository;

import com.ftn.railwayapp.model.account.BalanceAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceAccountRepository extends JpaRepository<BalanceAccount, Long> {
}
