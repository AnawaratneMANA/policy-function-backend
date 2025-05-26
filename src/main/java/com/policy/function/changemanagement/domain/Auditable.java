package com.policy.function.changemanagement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@MappedSuperclass
@Audited
@Getter
@Setter
public abstract class Auditable {

    @Column(updatable = false)
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
