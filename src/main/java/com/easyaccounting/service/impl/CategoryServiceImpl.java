package com.easyaccounting.service.impl;

import com.easyaccounting.dto.CategoryDTO;
import com.easyaccounting.entity.Category;
import com.easyaccounting.mapper.CategoryMapper;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.repository.CategoryRepository;
import com.easyaccounting.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final MapperUtil mapperUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, MapperUtil mapperUtil) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CategoryDTO> listAllCategories() {
//        List<Category> list = categoryRepository.findAll();
//        return list.stream().map(categoryMapper::convertToDto).collect(Collectors.toList());

        return categoryRepository.findAll().stream().map(category -> mapperUtil.convert(category, new CategoryDTO())).collect(Collectors.toList());
    }

}
