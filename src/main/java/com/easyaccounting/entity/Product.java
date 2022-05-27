package com.easyaccounting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@NoArgsConstructor
@Data
@Entity
@Where(clause = "is_deleted = false")
public class Product extends BaseEntity {

    private String name;
    private String description;
    private Long qty;
    private String unit;
    private Long low_limit_alert;
    private Long tax;
    private Boolean enabled;
    private String new_column;


//    @Enumerated(EnumType.STRING)
//    private Status product_status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


}
