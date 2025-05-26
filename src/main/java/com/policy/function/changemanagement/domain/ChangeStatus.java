package com.policy.function.changemanagement.domain;

import com.policy.function.changemanagement.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity(name = "change_status")
@Audited
@Getter
@Setter
public class ChangeStatus extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long changeStatusId;

    private String changeStatusName;

    @Enumerated(EnumType.STRING)
    private Status status;
}