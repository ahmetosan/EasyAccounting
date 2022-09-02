package com.easyaccounting.controller;

import com.easyaccounting.dto.ProductDTO;
import com.easyaccounting.enums.ProductStatus;
import com.easyaccounting.enums.UnitsType;
import com.easyaccounting.service.CategoryService;
import com.easyaccounting.service.CompanyService;
import com.easyaccounting.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final CompanyService companyService;

    public ProductController(ProductService productService, CategoryService categoryService, CompanyService companyService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.companyService = companyService;
    }


    @GetMapping("/list")
    public String getAllProducts(Model model){

        model.addAttribute("product",productService.listAllProducts());
        model.addAttribute("categories",categoryService.listAllCategories());
        model.addAttribute("company", companyService.getCurrentCompany().getTitle());

        return "/product/product-list";
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("company",companyService.getCurrentCompany().getTitle());
        model.addAttribute("products",productService.listAllProducts());
        model.addAttribute("unitTypes", UnitsType.values());
        model.addAttribute("productStatus", ProductStatus.values());
        model.addAttribute("categories",categoryService.listAllCategories());
        return "/product/product-add";
    }

    @PostMapping("/add")
    public String insertCategory(@ModelAttribute("product") ProductDTO productDTO, Model model) {
        model.addAttribute("company",companyService.getCurrentCompany().getTitle());
        model.addAttribute("products",productService.listAllProducts());
        model.addAttribute("unitTypes", UnitsType.values());
        model.addAttribute("product.Status", ProductStatus.values());
        model.addAttribute("categories",categoryService.listAllCategories());
        productService.save(productDTO);

        return "redirect:/product/list";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model){
        model.addAttribute("company",companyService.getCurrentCompany().getTitle());
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("unitTypes", UnitsType.values());
        model.addAttribute("productStatus", ProductStatus.values());
        model.addAttribute("categories",categoryService.listAllCategories());
        return "/product/product-edit";
    }

    @PostMapping("/edit")
    public String updateProduct(ProductDTO  product,Model model){

        model.addAttribute("company",companyService.getCurrentCompany().getTitle());
        model.addAttribute("products",productService.listAllProducts());
        model.addAttribute("unitTypes", UnitsType.values());
        model.addAttribute("productStatus", ProductStatus.values());
        model.addAttribute("categories",categoryService.listAllCategories());

        productService.update(product);

        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productService.delete(id);
        return "redirect:/product/list";
    }

}
