package com.easyaccounting.dto;

import com.easyaccounting.enums.CompanyType;
import com.easyaccounting.enums.State;
import com.easyaccounting.enums.ClientVendorType;
import com.easyaccounting.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientVendorDTO {
    private String companyName;
    private Long id;
    private String phone;
    private String email;
    private CompanyDTO company;
    private ClientVendorType type;
    private String zipCode;
    private String address;
    private State state;
    private Boolean enabled;

}
