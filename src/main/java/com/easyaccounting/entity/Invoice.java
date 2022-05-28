package com.easyaccounting.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "invoices")
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Invoice extends BaseEntity{


    private String invoiceNumber;
    private String invoiceStatus;
    private String invoiceType;
    @Column(columnDefinition = "DATE")
    private LocalDate invoiceDate;
//    Line 27-29 will be commented out after ClientVendor entity is created
//    @ManyToOne
//    @JoinColumn(name = "sptable_id")
//    private ClientVendor clientVendor;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private boolean enabled;

}