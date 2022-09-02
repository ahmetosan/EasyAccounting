package com.easyaccounting.service.impl;

import com.easyaccounting.dto.InvoiceProductDTO;
import com.easyaccounting.dto.ProductDTO;
import com.easyaccounting.entity.ClientVendor;
import com.easyaccounting.entity.Company;
import com.easyaccounting.entity.Product;
import com.easyaccounting.entity.common.UserPrincipal;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.mapper.ProductMapper;
import com.easyaccounting.repository.CompanyRepository;
import com.easyaccounting.repository.ProductRepository;
import com.easyaccounting.service.ProductService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;
    private final ProductMapper productMapper;
    private final CompanyRepository companyRepository;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil, ProductMapper productMapper, CompanyRepository companyRepository) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
        this.productMapper = productMapper;
        this.companyRepository = companyRepository;
    }


    @Override
    public List<ProductDTO> listAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> mapperUtil.convert(product,new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return mapperUtil.convert(product.get(),new ProductDTO());
        }
        return null;
    }

    @Override
    public void save(ProductDTO dto) {
        dto.setEnabled(true);
        productRepository.save(mapperUtil.convert(dto,new Product()));

    }

    @Override
    public void edit(Long id, ProductDTO dto) {
        Optional<Product> product = productRepository.findById(id);
        Product convertedProduct= productMapper.convertToEntity(dto);
        dto.setEnabled(true);
        if(product.isPresent()){
            convertedProduct.setId(id);
            productRepository.save(convertedProduct);
        }
    }

    @Override
    public void delete(Long id) {

        Product product=productRepository.getById(id);
        product.setIsDeleted(true);
        productRepository.save(product);
    }

    @Override
    public void update(ProductDTO dto) {
        Optional<Product> product=productRepository.findById(dto.getId());
        Product convertedProduct=productMapper.convertToEntity(dto);

            convertedProduct.setId(product.get().getId());
            convertedProduct.setProduct_status(product.get().getProduct_status());
            productRepository.save(convertedProduct);

    }

    @Override
    public List<ProductDTO> getAllProductsByCompany() {
        Company company = getCurrentCompany();
        return productRepository.findAllByCompanyId(company.getId())
                .stream()
                .map(obj -> mapperUtil.convert(obj, new ProductDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductByCompany() {
        Company company = getCurrentCompany();
        return mapperUtil.convert(productRepository.findById(company.getId()),new ProductDTO());
    }

    public Company getCurrentCompany() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return companyRepository.findById(userPrincipal.getLoggedInUserCompanyId()).get();
    }


}
