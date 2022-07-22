package com.easyaccounting.converter;

import com.easyaccounting.dto.CompanyDTO;
import com.easyaccounting.service.CompanyService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class CompanyDtoConverter implements Converter<String, CompanyDTO> {

    CompanyService companyService;


    public CompanyDtoConverter(@Lazy CompanyService companyService) {
        this.companyService = companyService;
    }


    @Override
    public CompanyDTO convert(String source) {
        if (source == null || source.equals("")) {
            return null;
        }

        return companyService.findCompanyById(Long.parseLong(source));
        //return companyService.findByCompanyTitle(source);
    }
}