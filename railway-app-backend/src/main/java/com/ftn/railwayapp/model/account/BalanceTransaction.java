package com.ftn.railwayapp.model.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "balance_transaction")
@Getter
@Setter
@NoArgsConstructor
public class BalanceTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timeStamp;

    @Column(nullable = false)
    private int tokensNum;

    @Column(nullable = false)
    private int moneySpent;

    @Column(nullable = false)
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="balance_account_id", nullable=false)
    @JsonIgnore
    private BalanceAccount balanceAccount;

    public BalanceTransaction(
                              int tokensNum,
                              int moneySpent,
                              String currency,
                              BalanceAccount balanceAccount
    ) {
        this.timeStamp = LocalDateTime.now();
        this.tokensNum = tokensNum;
        this.moneySpent = moneySpent;
        this.currency = currency;
        this.balanceAccount = balanceAccount;
    }
}
