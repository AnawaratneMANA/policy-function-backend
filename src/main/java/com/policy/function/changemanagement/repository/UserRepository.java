package com.policy.function.changemanagement.repository;

import com.policy.function.changemanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    @Query("SELECT u FROM users u WHERE u.userRole.roleName = 'Approver'")
    List<User> findAllApprovers();
}
