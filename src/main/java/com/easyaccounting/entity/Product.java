package com.easyaccounting.entity;

import com.easyaccounting.enums.ProductStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

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


    @Enumerated(EnumType.STRING)
    private ProductStatus product_status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

//new update on products
}