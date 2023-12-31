package com.ftn.railwayapp.repository.user;

import com.ftn.railwayapp.model.user.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegularUserRepository  extends JpaRepository<RegularUser, Long> {
    @Query("select ru from RegularUser ru where ru.email=?1")
    Optional<RegularUser> getRegularUserByEmail(String email);

    @Query("select ru from RegularUser ru where ru.id=?1 and ru.verified=true")
    Optional<RegularUser> getVerifiedRegularUserByEmail(Long id);

    @Query("select ru.balanceAccount.id from RegularUser ru where ru.id=?1 and ru.verified=true")
    Optional<Long> getBalanceAccountId(Long userId);
}
