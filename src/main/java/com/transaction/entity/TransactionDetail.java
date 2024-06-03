package com.transaction.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.transaction.constan.ConstantTable;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.BILL_DETAIL)
public class TransactionDetail {
    @Id
    @SequenceGenerator(
            name = "tdetail_sequence",
            sequenceName = "tdetail_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tdetail_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trx_id", nullable = false)
    @JsonBackReference
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false )
    private Menu menu;

    @Column(name = "qty", nullable = false)
    private Integer qty;



}
