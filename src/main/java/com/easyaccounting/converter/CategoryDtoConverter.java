package com.easyaccounting.converter;

import com.easyaccounting.dto.CategoryDTO;
import com.easyaccounting.service.CategoryService;
import org.springframework.core.convert.converter.Converter;

public class CategoryDtoConverter implements Converter<String, CategoryDTO> {

    private final CategoryService categoryService;


    public CategoryDtoConverter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Override
    public CategoryDTO convert(String source) {
        if (source == null || source.equals("")) {
            return null;
        }

//        return categoryService.xxx(source);
        return null;

    }
}
