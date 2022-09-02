package com.easyaccounting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

// do we need al arg constu
@NoArgsConstructor
@Data
@Entity
@Where(clause = "is_deleted = false")
@Table(name = "categories")
public class Category extends BaseEntity{


    private String description;
    @ManyToOne (fetch = FetchType.LAZY)//should we use the fetch lazy --- why we do not use the many to many ...
    @JoinColumn(name = "company_id")
    private Company company; //private  List<Company> company;
    private Boolean enabled;


}
