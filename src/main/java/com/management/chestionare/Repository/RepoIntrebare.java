package com.management.chestionare.Repository;

import com.management.chestionare.domain.Intrebare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoIntrebare extends JpaRepository<Intrebare, Long> {
}
