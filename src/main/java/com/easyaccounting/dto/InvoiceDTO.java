package com.easyaccounting.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    List<InvoiceProductDTO> invoiceProduct = new ArrayList<>();
    private int invoiceCost;
    private int invoiceTax;
    private int totalCost;
}
