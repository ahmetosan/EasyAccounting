package com.easyaccounting.dto;

import com.easyaccounting.enums.State;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@NoArgsConstructor
//@AllArgsConstructor

public class CompanyDTO {

    private Long id;
    private String title;
    private String address1;
    private String address2;
    private String zip;
    private String representative;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate establishmentDate;
    private Boolean enabled;
    private String phone;
    private State state;

    public CompanyDTO(Long id, String title, String address1, String address2, String zip,
                      String representative, String email, LocalDate establishmentDate,
                      Boolean enabled, String phone, State state) {
        this.id = id;
        this.title = title;
        this.address1 = address1;
        this.address2 = address2;
        this.zip = zip;
        this.representative = representative;
        this.email = email;
        this.establishmentDate = establishmentDate;
        this.enabled = enabled;
        this.phone = phone;
        this.state = state;
    }

}



