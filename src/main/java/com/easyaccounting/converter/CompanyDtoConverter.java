package com.easyaccounting.converter;

import com.easyaccounting.dto.CompanyDTO;
import com.easyaccounting.service.CompanyService;
import org.springframework.core.convert.converter.Converter;


public class CompanyDtoConverter implements Converter<String, CompanyDTO> {

    private final CompanyService companyService;


    public CompanyDtoConverter(CompanyService companyService) {
        this.companyService = companyService;
    }


    @Override
    public CompanyDTO convert(String source) {
        if (source == null || source.equals("")) {
            return null;
        }

        return null;
    }
}