package com.easyaccounting.repository;

import com.easyaccounting.entity.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {

    List<InvoiceProduct> findAllByInvoiceId(Long id);
    InvoiceProduct findInvoiceProductById(Long id);
}
