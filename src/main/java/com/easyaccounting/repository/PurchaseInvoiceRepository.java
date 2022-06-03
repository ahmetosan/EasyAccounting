package com.easyaccounting.repository;

import com.easyaccounting.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseInvoiceRepository  extends JpaRepository<Invoice, Long> {
}
