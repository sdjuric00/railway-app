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
}
