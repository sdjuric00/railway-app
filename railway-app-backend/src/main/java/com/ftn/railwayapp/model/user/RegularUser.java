package com.ftn.railwayapp.model.user;

import com.ftn.railwayapp.model.account.BalanceAccount;
import com.ftn.railwayapp.model.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "regular_user")
@Getter
@Setter
@NoArgsConstructor
public class RegularUser extends User {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "balance_account_id")
    private BalanceAccount balanceAccount;

    public RegularUser(String email,
                       String password,
                       String fullName,
                       Gender gender,
                       Address address,
                       Role role,
                       boolean verified,
                       BalanceAccount balanceAccount,
                       boolean socialAccount
    ) {
        super(email, password, fullName, gender, address, role, verified, socialAccount);
        this.balanceAccount = balanceAccount;
    }

}
