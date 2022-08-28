package com.joonseolee.musinsasearchassignment.service;

import com.joonseolee.musinsasearchassignment.entity.ProductMapper;
import com.joonseolee.musinsasearchassignment.exception.MusinsaException;
import com.joonseolee.musinsasearchassignment.model.*;
import com.joonseolee.musinsasearchassignment.repository.BrandRepository;
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
    private final BrandRepository brandRepository;
    private final ProductMapper productMapper;

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

    public InsertedProduct.Response insertProduct(InsertedProduct.Request request) {
        if (Objects.isNull(request)) {
            throw new MusinsaException(ErrorStatusType.NOT_FOUND_PARAMETERS_400);
        }
        var product = productMapper.toProduct(request);
        var brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new MusinsaException(ErrorStatusType.NOT_FOUND_DATA_500));
        product.setBrand(brand);
        var productCategory = productCategoryRepository.findById(request.getProductCategoryId())
                .orElseThrow(() -> new MusinsaException(ErrorStatusType.NOT_FOUND_DATA_500));
        product.setProductCategory(productCategory);

        var savedProduct = productRepository.save(product);
        return productMapper.toInsertedProductResponse(savedProduct);
    }

    public void updateProduct(long id, UpdatedProduct.Request request) {
        if (Objects.isNull(request)) {
            throw new MusinsaException(ErrorStatusType.NOT_FOUND_PARAMETERS_400);
        }

        var product = productRepository.findById(id)
                .orElseThrow(() -> new MusinsaException(ErrorStatusType.NOT_FOUND_DATA_500));

        productMapper.updateProductFromUpdatedProductRequest(product, request);

        if (!Objects.isNull(request.getBrandId())) {
            var brand = brandRepository.findById(request.getBrandId())
                    .orElseThrow(() -> new MusinsaException(ErrorStatusType.NOT_FOUND_DATA_500));
            product.setBrand(brand);
        }

        if (!Objects.isNull(request.getProductCategoryId())) {
            var productCategory = productCategoryRepository.findById(request.getProductCategoryId())
                    .orElseThrow(() -> new MusinsaException(ErrorStatusType.NOT_FOUND_DATA_500));
            product.setProductCategory(productCategory);
        }

        productRepository.save(product);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
