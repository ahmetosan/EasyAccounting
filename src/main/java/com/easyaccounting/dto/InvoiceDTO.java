package com.easyaccounting.dto;

import com.easyaccounting.entity.ClientVendor;
import com.easyaccounting.entity.Company;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    public InvoiceDTO(String invoiceNumber, String invoiceStatus, String invoiceType, LocalDate invoiceDate, ClientVendorDTO clientVendor, CompanyDTO company, boolean enabled) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceStatus = invoiceStatus;
        this.invoiceType = invoiceType;
        this.invoiceDate = invoiceDate;
        this.clientVendor = clientVendor;
        this.company = company;
        this.enabled = enabled;
    }
}
