package com.easyaccounting.controller;

import com.easyaccounting.dto.ProductDTO;
import com.easyaccounting.entity.Product;
import com.easyaccounting.service.CategoryService;
import com.easyaccounting.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String getAllProducts(Model model){

        model.addAttribute("product",productService.listAllProducts());
        model.addAttribute("categories",categoryService.listAllCategories());

        return "/product/product-list";
    }

    @GetMapping("/add")
    public String addProduct(Model model){

        return "/product/product-add";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") String id){

        return "/product/product-edit";

    }

    @GetMapping("/create")
    public String createProduct(Model model){
        model.addAttribute("products", new ProductDTO());
        model.addAttribute("product",productService.listAllProducts());
        model.addAttribute("categories",categoryService.listAllCategories());

        return "/product/product-add";
    }
}
