package com.easyaccounting.converter;

import com.easyaccounting.dto.ProductDTO;
import com.easyaccounting.service.ProductService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
@ConfigurationPropertiesBinding
public class ProductDtoConverter implements Converter<String, ProductDTO> {

    private final ProductService productService;

    public ProductDtoConverter(@Lazy ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ProductDTO convert(String source) {
//        System.out.println(source + 1234567890);
        if (source.equals("")) {
            return null;
        }

        return productService.findById(Long.parseLong(source));
    }
}