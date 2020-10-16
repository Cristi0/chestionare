package com.management.chestionare.Repository;

import com.management.chestionare.domain.ChestionarEfectuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoChestionarEfectuat extends JpaRepository<ChestionarEfectuat, Long> {
}
