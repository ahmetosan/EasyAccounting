package com.easyaccounting.mapper;

import com.easyaccounting.dto.CompanyDTO;
import com.easyaccounting.entity.Company;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {


    private final ModelMapper modelMapper;

    public CompanyMapper(ModelMapper modelMapper) {

        this.modelMapper = modelMapper;
    }

    public Company convertToEntity(CompanyDTO dto){

        return modelMapper.map(dto,Company.class);

    }

    public CompanyDTO convertToDto(Company entity){

        return modelMapper.map(entity,CompanyDTO.class);
    }
}
