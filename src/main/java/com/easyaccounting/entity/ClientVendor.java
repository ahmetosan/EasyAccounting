package com.easyaccounting.entity;


import com.easyaccounting.enums.ClientVendorType;
import com.easyaccounting.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Table(name = "client_vendor")
@Getter
@Setter
@NoArgsConstructor
@Entity
@Where(clause ="is_deleted=false")
public class ClientVendor extends BaseEntity{

    private String companyName;
    private String phone;
    @Column(name = "email")
    private String emailAddress;
    @Enumerated(EnumType.STRING)
    private ClientVendorType clientVendorType;
    private String zipCode;
    private String address;
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Enumerated(EnumType.STRING)
    private State state;

}
