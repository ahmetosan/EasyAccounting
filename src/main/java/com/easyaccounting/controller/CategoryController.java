package com.easyaccounting.controller;

import com.easyaccounting.dto.CategoryDTO;
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
    @GetMapping("/add")
    public String addCategory(Model model, CategoryDTO categoryDTO) {
        model.addAttribute("category", new CategoryDTO());
        return "/category/category-add";
    }

    @PostMapping("/add")
    public String insertCategory(@ModelAttribute("category") CategoryDTO category, Model model) {
        model.addAttribute("category", new CategoryDTO());
        categoryService.save(category);

        return "redirect:/category";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, CategoryDTO dto) {

        categoryService.edit(id, dto);
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Long categoryId, Model model) {

        model.addAttribute("category", categoryService.findById(categoryId));

        return "/category/category-edit";

    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long categoryId) {
        categoryService.delete(categoryId);
        return "redirect:/category";
    }

}
