package com.easyaccounting.dto;

import com.easyaccounting.enums.MonthName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Long id;
    private LocalDate year;
    private Double amount;
    private Boolean isPaid;
    private String institutionId;
    private MonthName month;

}
