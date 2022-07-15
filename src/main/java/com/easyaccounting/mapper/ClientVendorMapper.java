package com.easyaccounting.mapper;

import com.easyaccounting.dto.ClientVendorDTO;
import com.easyaccounting.entity.ClientVendor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientVendorMapper {
    private final ModelMapper modelMapper;

    public ClientVendorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClientVendor convertToEntity(ClientVendorDTO dto){

        return modelMapper.map(dto,ClientVendor.class);

    }

    public ClientVendorDTO convertToDto(ClientVendor entity){

        return modelMapper.map(entity,ClientVendorDTO.class);
    }
}
