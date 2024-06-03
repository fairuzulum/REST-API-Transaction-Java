package com.transaction.entity;


import com.transaction.constan.ConstantTable;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = ConstantTable.CUSTOMER)
public class Customer {
    @Id
    @GeneratedValue(generator = "customer-id")
    @GenericGenerator(name = "customer-id", strategy = "com.transaction.utils.CustomerIdCustomGenerator")
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_member")
    private Boolean isMember;

    @OneToOne
    @JoinColumn(name = "user_account_id", unique = true)
    private UserAccount userAccount;


}
