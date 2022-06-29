package com.easyaccounting.service;

import com.easyaccounting.dto.ProductDTO;
import com.easyaccounting.entity.Company;

import java.util.List;


public interface ProductService {


    List<ProductDTO> listAllProducts();
    ProductDTO findByName(String name);
    List<ProductDTO> getAllProductsByCompany();
}
