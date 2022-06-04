package com.easyaccounting.mapper;


import com.easyaccounting.dto.SalesInvoiceDTO;
import com.easyaccounting.entity.Invoice;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SalesInvoiceMapper {


    private final ModelMapper modelMapper;

    public SalesInvoiceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Invoice convertToEntity(SalesInvoiceDTO dto){

        return modelMapper.map(dto,Invoice.class);

    }

    public SalesInvoiceDTO convertToDto(Invoice entity){

        return modelMapper.map(entity,SalesInvoiceDTO.class);
    }
}
