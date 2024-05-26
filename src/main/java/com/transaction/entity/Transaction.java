package com.transaction.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.transaction.constan.ConstantTable;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ConstantTable.BILL)
public class Transaction {
    @Id
    @GeneratedValue(generator = "transaction-id")
    @GenericGenerator(name = "transaction-id", strategy = "com.transaction.utils.TransactionIdCustomGenerator")
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id" )
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "trans_type_id" )
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "table_id" )
    private com.transaction.entity.Table table;

    @OneToMany(mappedBy = "transaction")
    @JsonManagedReference
    private List<TransactionDetail> transactionDetails;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "trans_date", updatable = false)
    private Date transDate;


}
