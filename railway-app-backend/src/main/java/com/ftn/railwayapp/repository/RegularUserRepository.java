package com.ftn.railwayapp.repository;

import com.ftn.railwayapp.model.user.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularUserRepository  extends JpaRepository<RegularUser, Long> {
}
