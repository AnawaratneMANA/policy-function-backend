package com.policy.function.changemanagement.repository;

import com.policy.function.changemanagement.domain.ChangeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeStatusRepository extends JpaRepository<ChangeStatus, Long> {
}
