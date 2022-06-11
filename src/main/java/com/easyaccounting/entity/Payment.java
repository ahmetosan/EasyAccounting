package com.easyaccounting.entity;

import com.easyaccounting.enums.MonthName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Where(clause = "is_deleted=false")
public class Payment extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private MonthName month;

//    @Column(columnDefinition = "DATE")
    private String year;
    private Double amount;
    private Boolean isPaid;
    private String institutionId;


    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
}
