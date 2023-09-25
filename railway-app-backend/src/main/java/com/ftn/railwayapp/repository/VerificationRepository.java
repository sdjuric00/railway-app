package com.ftn.railwayapp.repository;

import com.ftn.railwayapp.model.user.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long> {

    @Query("select v from Verification v where v.hashedId=?1")
    Optional<Verification> findByHashedId(String hashedId);
}
