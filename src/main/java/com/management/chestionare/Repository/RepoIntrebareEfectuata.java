package com.management.chestionare.Repository;

import com.management.chestionare.domain.IntrebareEfectuata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoIntrebareEfectuata extends JpaRepository<IntrebareEfectuata, Long> {
}
