package com.easyaccounting.repository;

import com.easyaccounting.entity.Invoice;
import com.easyaccounting.enums.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseInvoiceRepository  extends JpaRepository<Invoice, Long> {

    List<Invoice> findInvoicesByInvoiceType(InvoiceType invoiceType);
}
