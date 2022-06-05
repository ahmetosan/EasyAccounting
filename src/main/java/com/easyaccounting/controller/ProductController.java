package com.easyaccounting.controller;

import com.easyaccounting.service.CategoryService;
import com.easyaccounting.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/product")
    public String getAllProducts(Model model){

        model.addAttribute("product",productService.listAllProducts());
        model.addAttribute("categories",categoryService.listAllCategories());

        return "/product/product-list";
    }
}
