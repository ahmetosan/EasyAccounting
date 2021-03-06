package com.easyaccounting.entity;

import com.easyaccounting.enums.State;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
@Where(clause = "is_deleted = false")
@Table(name = "companies")
public class Company extends BaseEntity {

    private String title;
    private String address1;
    private String address2;
    private String zip;
    private String representative;
    private String email;
    @Column(columnDefinition = "DATE")
    private LocalDate establishmentDate;
    private Boolean enabled;
    private String phone;

    @Enumerated(EnumType.STRING)
    private State state;

}
