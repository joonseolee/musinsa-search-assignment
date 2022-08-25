package com.joonseolee.musinsasearchassignment.controller;

import com.joonseolee.musinsasearchassignment.model.BrandLowest;
import com.joonseolee.musinsasearchassignment.model.ProductLowest;
import com.joonseolee.musinsasearchassignment.model.ProductLowestHighest;
import com.joonseolee.musinsasearchassignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/products")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/classifications/categories")
    public ProductLowest.Response getLowestProducts() {
        return productService.getLowestProducts();
    }

    @GetMapping("/classifications/categories/{categoryId}")
    public ProductLowestHighest.Response getLowestProductsByCategory(@PathVariable Long categoryId) {
        return productService.getLowestProductByCategory(categoryId);
    }

    @GetMapping("/classifications/brands")
    public BrandLowest.Response getLowestBrand() {
        return productService.getLowestBrand();
    }
}
