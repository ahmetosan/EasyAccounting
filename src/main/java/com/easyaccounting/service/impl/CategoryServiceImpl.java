package com.easyaccounting.service.impl;

import com.easyaccounting.dto.CategoryDTO;
import com.easyaccounting.entity.Category;
import com.easyaccounting.mapper.CategoryMapper;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.repository.CategoryRepository;
import com.easyaccounting.service.CategoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

    @Override
    public CategoryDTO findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return categoryMapper.convertToDto(category.get());
        }
        return null;
    }


    @Override
    public void save(CategoryDTO dto) {
        Category category = categoryMapper.convertToEntity(dto);
        dto.setEnabled(true);
        categoryRepository.save(category);
    }

    public void edit(Long id, CategoryDTO dto) {


        Optional<Category> category = categoryRepository.findById(id);
//        dto.setId(id);
        Category convertedCategory = categoryMapper.convertToEntity(dto);
        dto.setEnabled(true);
        if(category.isPresent()){
            convertedCategory.setId(id);
            categoryRepository.save(convertedCategory);
        }

    }

    @Override
    public void delete(Long id) {

        Optional<Category> foundCategory = categoryRepository.findById(id);

        if(foundCategory.isPresent()){
            foundCategory.get().setIsDeleted(true);
            categoryRepository.save(foundCategory.get());
        }


    }

}
