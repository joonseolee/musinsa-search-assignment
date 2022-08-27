package com.joonseolee.musinsasearchassignment.controller;

import com.joonseolee.musinsasearchassignment.model.BaseResponse;
import com.joonseolee.musinsasearchassignment.model.BrandLowest;
import com.joonseolee.musinsasearchassignment.model.ProductLowest;
import com.joonseolee.musinsasearchassignment.model.ProductLowestHighest;
import com.joonseolee.musinsasearchassignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/products")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private ProductController proxy;

    @Autowired
    public void setProxy(ProductController proxy) {
        this.proxy = proxy;
    }

    @GetMapping("/classifications/categories")
    public BaseResponse<ProductLowest.Response> getLowestProducts() {
        return new BaseResponse<>(proxy.getLowestProductsCache());
    }

    @Cacheable(value = "ProductLowestResponse", key = "#root.method.name")
    public ProductLowest.Response getLowestProductsCache() {
        return productService.getLowestProducts();
    }

    @GetMapping("/classifications/categories/{categoryId}")
    public BaseResponse<ProductLowestHighest.Response> getLowestProductsByCategory(@PathVariable Long categoryId) {
        return new BaseResponse<>(productService.getLowestProductByCategory(categoryId));
    }

    @GetMapping("/classifications/brands")
    public BaseResponse<BrandLowest.Response> getLowestBrand() {
        return new BaseResponse<>(productService.getLowestBrand());
    }
}
