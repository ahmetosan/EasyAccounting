package com.easyaccounting.service.impl;

import com.easyaccounting.dto.ProductDTO;
import com.easyaccounting.entity.Category;
import com.easyaccounting.entity.Product;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.mapper.ProductMapper;
import com.easyaccounting.repository.ProductRepository;
import com.easyaccounting.service.ProductService;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
        this.productMapper = productMapper;
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
        Product product=productMapper.convertToEntity(dto);
        dto.setEnabled(true);
        productRepository.save(product);
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
        Optional<Product> foundProduct = productRepository.findById(id);

        if(foundProduct.isPresent()){
            foundProduct.get().setIsDeleted(true);
            productRepository.save(foundProduct.get());
        }
    }

    @Override
    public void update(ProductDTO dto) {
        Optional<Product> product=productRepository.findById(dto.getId());
        Product convertedProduct=productMapper.convertToEntity(dto);

        if (product.isPresent()){
            convertedProduct.setId(product.get().getId());
            convertedProduct.setProduct_status(dto.getProductStatus()==null ? product.get().getProduct_status() : dto.getProductStatus());
            productRepository.save(convertedProduct);
        }
    }


}
