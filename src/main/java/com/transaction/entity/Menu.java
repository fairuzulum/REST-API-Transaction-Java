package com.transaction.entity;


import com.transaction.constan.ConstantTable;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.MENU)
public class Menu {
    @Id
    @GeneratedValue(generator = "menu-id")
    @GenericGenerator(name = "menu-id", strategy = "com.transaction.utils.MenuIdCustomGenerator")
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;
}
