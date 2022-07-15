package com.easyaccounting.mapper;


import com.easyaccounting.dto.ProductDTO;
import com.easyaccounting.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public Product convertToEntity(ProductDTO dto){

        return modelMapper.map(dto,Product.class);

    }

    public ProductDTO convertToDto(Product entity){

        return modelMapper.map(entity,ProductDTO.class);
    }
}
