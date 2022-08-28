package com.joonseolee.musinsasearchassignment.controller;

import com.joonseolee.musinsasearchassignment.model.*;
import com.joonseolee.musinsasearchassignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/products")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PutMapping
    @GetMapping("/classifications/categories")
    public BaseResponse<ProductLowest.Response> getLowestProducts() {
        return new BaseResponse<>(productService.getLowestProducts());
    }

    @GetMapping("/classifications/categories/{categoryId}")
    public BaseResponse<ProductLowestHighest.Response> getLowestProductsByCategory(@PathVariable long categoryId) {
        return new BaseResponse<>(productService.getLowestProductByCategory(categoryId));
    }

    @GetMapping("/classifications/brands")
    public BaseResponse<BrandLowest.Response> getLowestBrand() {
        return new BaseResponse<>(productService.getLowestBrand());
    }

    @PostMapping
    public BaseResponse<InsertedProduct.Response> insertProduct(@RequestBody InsertedProduct.Request request) {
        return new BaseResponse<>(productService.insertProduct(request));
    }

    @PutMapping("/{id}")
    public BaseResponse<Void> updateProduct(@PathVariable long id, @RequestBody UpdatedProduct.Request request) {
        productService.updateProduct(id, request);
        return BaseResponse.ok();
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return BaseResponse.ok();
    }
}
