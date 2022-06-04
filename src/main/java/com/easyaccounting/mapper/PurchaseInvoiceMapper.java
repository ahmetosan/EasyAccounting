package com.easyaccounting.mapper;

import com.easyaccounting.dto.PurchaseInvoiceDTO;
import com.easyaccounting.entity.Invoice;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PurchaseInvoiceMapper {

    private final ModelMapper modelMapper;

    public PurchaseInvoiceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Invoice convertToEntity(PurchaseInvoiceDTO dto){

        return modelMapper.map(dto,Invoice.class);

    }

    public PurchaseInvoiceDTO convertToDto(Invoice entity){

        return modelMapper.map(entity,PurchaseInvoiceDTO.class);
    }
}
