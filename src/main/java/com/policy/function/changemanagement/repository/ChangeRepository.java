package com.policy.function.changemanagement.repository;

import com.policy.function.changemanagement.domain.Change;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangeRepository extends JpaRepository<Change, Long> {
    List<Change> findByCreatedBy(Long userId);
    List<Change> findByApproverId(Long approverId);
}
