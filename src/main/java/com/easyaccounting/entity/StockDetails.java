package com.easyaccounting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

    @NoArgsConstructor
    @Data
    @Entity
    @Where(clause = "is_deleted = false")
    public class StockDetails extends BaseEntity {

        @Column(columnDefinition = "DATE")
        private LocalDate i_date;
        private int quantity;
        private int price;
        private int remaining_quantity;

//        Uncomment when enum will be created
//        @ManyToOne
//        @JoinColumn(name = "product_id")
//        private Product product;

}


