package com.management.chestionare.Repository;

import com.management.chestionare.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUser extends JpaRepository<User, Integer> {
}
