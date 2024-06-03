package com.transaction.entity;


import com.transaction.constan.ConstantTable;
import com.transaction.constan.UserRole;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.USER_ROLE)
public class Role {
    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    private Long id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
