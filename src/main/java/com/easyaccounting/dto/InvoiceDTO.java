package com.easyaccounting.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceDTO {

    private Long id;
    private String invoiceNumber;
    private String invoiceStatus;
    private String invoiceType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate invoiceDate;
    @NotNull
    private ClientVendorDTO clientVendor;
    @NotNull
    private CompanyDTO company;
    private boolean enabled;

    private int invoiceCost;
    private int invoiceTax;
    private int totalCost;
}
