package com.easyaccounting.entity;


import com.easyaccounting.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Table(name = "client_vendor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Where(clause ="is_deleted=false")
public class ClientVendor extends BaseEntity{

    private String companyName;
    private String phone;
    @Column(name = "email")
    private String emailAddress;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String zipCode;
    private String address;
    private Boolean enabled;

    // Delete after push

}
