package com.easyaccounting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Data
@Entity
@Where(clause = "is_deleted = false")
public class Category extends BaseEntity{


    private String description;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private Boolean enabled;


}