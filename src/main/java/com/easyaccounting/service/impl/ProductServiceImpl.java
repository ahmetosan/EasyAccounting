package com.easyaccounting.service.impl;

import com.easyaccounting.dto.CategoryDTO;
import com.easyaccounting.dto.ProductDTO;
import com.easyaccounting.entity.Product;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.repository.ProductRepository;
import com.easyaccounting.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<ProductDTO> listAllProducts() {

        return  productRepository.findAll().stream()
                .map(category -> mapperUtil.convert(category, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findByName(String name) {
        return null;
    }
}
