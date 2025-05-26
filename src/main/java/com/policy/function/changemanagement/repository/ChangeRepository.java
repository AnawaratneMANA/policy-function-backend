package com.policy.function.changemanagement.repository;

import com.policy.function.changemanagement.domain.Change;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeRepository extends JpaRepository<Change, Long> {

}
