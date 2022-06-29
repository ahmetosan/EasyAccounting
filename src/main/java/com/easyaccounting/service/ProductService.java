package com.easyaccounting.service;

import com.easyaccounting.dto.CategoryDTO;
import com.easyaccounting.dto.ProductDTO;

import java.util.List;


public interface ProductService{

    List<ProductDTO> listAllProducts();
    ProductDTO findById(Long id);
    void save(ProductDTO dto);
    void edit(Long id, ProductDTO dto);
    void delete(Long id);
    void update(ProductDTO dto);
}
