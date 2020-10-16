package com.management.chestionare.Repository;

import com.management.chestionare.domain.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUtilizator extends JpaRepository<Utilizator, Long> {
}
