package com.easyaccounting.service;

import com.easyaccounting.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {

    List<PaymentDTO> findAll();
    List<PaymentDTO> findAllByYear(String year);

    PaymentDTO findPaymentById(Long id);

    void chargePaymentById(Long id);
}