package com.ftn.railwayapp.model.account;

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
    private BalanceAccount balanceAccount;

    public BalanceTransaction(LocalDateTime timeStamp,
                              int tokensNum,
                              int moneySpent,
                              String currency
    ) {
        this.timeStamp = timeStamp;
        this.tokensNum = tokensNum;
        this.moneySpent = moneySpent;
        this.currency = currency;
    }
}
