package com.policy.function.changemanagement.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity(name = "users")
@Audited
@Setter
@Getter
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String userName;

    private String userPassword;

    private String userStatus;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role userRole;
}
