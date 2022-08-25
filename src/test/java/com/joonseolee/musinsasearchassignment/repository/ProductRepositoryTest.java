package com.joonseolee.musinsasearchassignment.repository;

import com.joonseolee.musinsasearchassignment.config.TestDataSourceConfig;
import com.joonseolee.musinsasearchassignment.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import(TestDataSourceConfig.class)
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    void givenNothing_whenFindAll_thenSuccess() {
        var products = productRepository.findAll();

        assertNotNull(products);
        assertEquals(72, products.size());
    }

    @Test
    void givenNothing_whenFindLowestProductsEachCategory_thenSuccess() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        var products = productRepository.findLowestByProductCategory(productCategories.get(3));

        assertNotNull(products);
    }

    @Test
    void givenNothing_whenFindLowestGroupByBrand_thenSuccess() {
        var result = productRepository.findLowestGroupByBrand();

        assertEquals("D", result.getBrandName());
        assertEquals(36100, result.getTotalPrice());
    }

    @Test
    void givenNothing_whenFindHighestByProductCategory_thenSuccess() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        var result = productRepository.findHighestByProductCategory(productCategories.get(0));

        assertEquals("I", result.getBrand().getName());
        assertEquals(11400, result.getPrice());
    }
}