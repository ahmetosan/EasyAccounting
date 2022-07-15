package com.easyaccounting.mapper;

import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.entity.Invoice;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PurchaseInvoiceMapper {

    private final ModelMapper modelMapper;

    public PurchaseInvoiceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Invoice convertToEntity(InvoiceDTO dto){

        return modelMapper.map(dto,Invoice.class);

    }

    public InvoiceDTO convertToDto(Invoice entity){

        return modelMapper.map(entity, InvoiceDTO.class);
    }
}
