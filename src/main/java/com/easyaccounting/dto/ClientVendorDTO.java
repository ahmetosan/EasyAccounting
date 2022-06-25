package com.easyaccounting.dto;

import com.easyaccounting.enums.CompanyType;
import com.easyaccounting.enums.State;
import lombok.Data;

@Data
public class ClientVendorDTO {
    private String type;
    private String companyName;
    private Long id;
    private String phone;
    private String email;
    private CompanyDTO company;
 //   private CompanyType type;
    private String zipCode;
    private String address;
    private State state;
    private Boolean enabled;
}
