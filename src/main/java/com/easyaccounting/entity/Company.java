package com.easyaccounting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
@Where(clause = "is_deleted = false")
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

    // Uncomment and review when enums will be added
//    @Enumerated(EnumType.STRING)
//    private State state;


}
