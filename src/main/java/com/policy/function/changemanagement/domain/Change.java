package com.policy.function.changemanagement.domain;

import com.policy.function.changemanagement.enums.CriticalityLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity(name = "change")
@Audited
@Setter
@Getter
public class Change extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long changeId;

    private String changeName;

    private String changeDescription;

    @Enumerated(EnumType.STRING)
    private CriticalityLevel criticalityLevel;

    private Long createdBy;

    private Long approverId;

    @ManyToOne
    private ChangeStatus status;
}
