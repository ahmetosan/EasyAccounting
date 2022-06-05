package com.easyaccounting.repository;

import com.easyaccounting.entity.Invoice;
import com.easyaccounting.enums.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesInvoiceRepository extends JpaRepository<Invoice, Long> {
       List<Invoice> findInvoiceByInvoiceType(InvoiceType invoiceType);
}
