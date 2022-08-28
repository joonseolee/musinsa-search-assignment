package com.joonseolee.musinsasearchassignment.service;

import com.joonseolee.musinsasearchassignment.exception.MusinsaException;
import com.joonseolee.musinsasearchassignment.model.BrandLowest;
import com.joonseolee.musinsasearchassignment.model.ErrorStatusType;
import com.joonseolee.musinsasearchassignment.model.ProductLowest;
import com.joonseolee.musinsasearchassignment.model.ProductLowestHighest;
import com.joonseolee.musinsasearchassignment.repository.ProductCategoryRepository;
import com.joonseolee.musinsasearchassignment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Cacheable(value = "ProductLowestResponse", key = "#root.method.name")
    public ProductLowest.Response getLowestProducts() {
        var productCategories = productCategoryRepository.findAll();

        List<ProductLowest.BriefProduct> products = new ArrayList<>();
        long totalPrice = 0;
        for (var productCategory : productCategories) {
            var product = productRepository.findLowestByProductCategory(productCategory);
            if (Objects.isNull(product)) {
                continue;
            }

            products.add(new ProductLowest.BriefProduct(
                    productCategory.getMainName(),
                    product.getBrand().getName(),
                    product.getPrice()));
            totalPrice += product.getPrice();
        }

        return new ProductLowest.Response(products, totalPrice);
    }

    public BrandLowest.Response getLowestBrand() {
        return productRepository.findLowestGroupByBrand();
    }

    public ProductLowestHighest.Response getLowestProductByCategory(Long categoryId) {
        var productCategory = productCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new MusinsaException(ErrorStatusType.NOT_FOUND_DATA_500));

        var lowestProduct = productRepository.findLowestByProductCategory(productCategory);
        var highestProduct = productRepository.findHighestByProductCategory(productCategory);

        return new ProductLowestHighest.Response(
                lowestProduct.getBrand().getName(),
                lowestProduct.getPrice(),
                highestProduct.getBrand().getName(),
                highestProduct.getPrice());
    }
}
