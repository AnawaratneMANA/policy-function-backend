package com.policy.function.changemanagement.repository;

import com.policy.function.changemanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
