package com.easyaccounting.controller;

import com.easyaccounting.service.CategoryService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @RequestMapping
    public String openCategory(Model model) {
        model.addAttribute("categories", categoryService.listAllCategories());
        return "/category/category-list";
    }

    // review and update during crud work
    @RequestMapping("/add")
    public String addCategory() {
        return "/category/category-add";
    }

    // review and update during crud work
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") String id) {

        return "/category/category-edit";

    }

}
