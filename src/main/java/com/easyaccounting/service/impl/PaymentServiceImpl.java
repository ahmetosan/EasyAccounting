package com.easyaccounting.service.impl;

import com.easyaccounting.dto.PaymentDTO;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.repository.PaymentRepository;
import com.easyaccounting.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final MapperUtil mapperUtil;

    public PaymentServiceImpl(PaymentRepository paymentRepository, MapperUtil mapperUtil) {
        this.paymentRepository = paymentRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<PaymentDTO> findAll() {
        return paymentRepository
                .findAll()
                .stream()
                .map(payment -> mapperUtil.convert(payment, new PaymentDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> findAllByYear(String year) {
        return paymentRepository
                .findAllByYear(year)
                .stream()
                .map(payment -> mapperUtil.convert(payment, new PaymentDTO()))
                .collect(Collectors.toList());

    }
}
