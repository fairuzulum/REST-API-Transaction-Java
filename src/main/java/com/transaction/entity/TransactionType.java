package com.transaction.entity;


import com.transaction.constan.ConstantTable;
import com.transaction.constan.TransType;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = ConstantTable.TRANSACTION_TYPE)
public class TransactionType {
    @Id
    @SequenceGenerator(
            name = "type_sequence",
            sequenceName = "type_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "type_sequence"
    )
    private Long id;

    @Column(name = "description", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private TransType description;
}
