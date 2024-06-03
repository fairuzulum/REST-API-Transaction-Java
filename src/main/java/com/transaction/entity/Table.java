package com.transaction.entity;


import com.transaction.constan.ConstantTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@jakarta.persistence.Table(name = ConstantTable.TABLE)
public class Table {
    @Id
    @SequenceGenerator(
            name = "table_sequence",
            sequenceName = "table_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "table_sequence"
    )
    private Long id;

    @Column(name = "table_name")
    private String tableName;


}
