package com.easyaccounting.service;

import com.easyaccounting.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> listAllCategories();

    CategoryDTO findById(Long id);

    void save(CategoryDTO dto);
    void edit(Long id, CategoryDTO dto);
    void delete(Long id);
}
