package com.easyaccounting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientVendorDTO {
    private Long id;
    private String type;
    private String companyName;

    public ClientVendorDTO(Long id, String type, String companyName) {
        this.id = id;
        this.type = type;
        this.companyName = companyName;
    }
}
