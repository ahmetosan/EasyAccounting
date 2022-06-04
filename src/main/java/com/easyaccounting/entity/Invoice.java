package com.easyaccounting.entity;


import com.easyaccounting.enums.InvoiceStatus;
import com.easyaccounting.enums.InvoiceType;
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
    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;
    @Enumerated(EnumType.STRING)
    private InvoiceType invoiceType;
    @Column(columnDefinition = "DATE")
    private LocalDate invoiceDate;
    @ManyToOne
    private ClientVendor clientVendor;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private boolean enabled;

}