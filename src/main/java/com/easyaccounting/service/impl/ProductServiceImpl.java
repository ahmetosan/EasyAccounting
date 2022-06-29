package com.easyaccounting.service.impl;

import com.easyaccounting.dto.ProductDTO;
import com.easyaccounting.entity.Company;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.repository.CompanyRepository;
import com.easyaccounting.repository.ProductRepository;
import com.easyaccounting.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;
    private final CompanyRepository companyRepository;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil, CompanyRepository companyRepository) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
        this.companyRepository = companyRepository;
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

    @Override
    public List<ProductDTO> getAllProductsByCompany() {
        Company company = getCurrentCompany();
        return productRepository.findAllByCompanyId(company.getId()).stream()
                .map(category -> mapperUtil.convert(category, new ProductDTO())).collect(Collectors.toList());
    }

    public Company getCurrentCompany() {
// ToDO find current user, to return company info logged in by current user
        return companyRepository.findById(1L).get();
    }
}
