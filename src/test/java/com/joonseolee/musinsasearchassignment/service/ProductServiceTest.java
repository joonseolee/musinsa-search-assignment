package com.joonseolee.musinsasearchassignment.service;

import com.joonseolee.musinsasearchassignment.entity.Brand;
import com.joonseolee.musinsasearchassignment.entity.Product;
import com.joonseolee.musinsasearchassignment.entity.ProductCategory;
import com.joonseolee.musinsasearchassignment.model.BrandLowest;
import com.joonseolee.musinsasearchassignment.repository.ProductCategoryRepository;
import com.joonseolee.musinsasearchassignment.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @Test
    void givenNothing_whenGetLowestBrand_thenSuccess() {
        var response = new BrandLowest.Response("꼴찌", 4000L);
        when(productRepository.findLowestGroupByBrand()).thenReturn(response);

        var result = productService.getLowestBrand();

        assertNotNull(response);
        assertEquals("꼴찌", result.getBrandName());
        assertEquals(4000L, result.getTotalPrice());
    }

    @Test
    void givenProductCategoryIsEmpty_whenGetLowestProducts_thenEmptyObject() {
        when(productCategoryRepository.findAll()).thenReturn(Collections.emptyList());

        var result = productService.getLowestProducts();

        assertNotNull(result);
        assertEquals(0, result.getProducts().size());
        assertEquals(0, result.getTotalPrice());
    }

    @Test
    void givenNothing_whenGetLowestProducts_thenSuccess() {
        when(productCategoryRepository.findAll()).thenReturn(generateProductCategories());
        when(productRepository.findLowestByProductCategory(any(ProductCategory.class)))
                .thenReturn(new Product(1L, "상의_셔츠", 32000L, new Brand(1L, "셔츠브랜드", null), null))
                .thenReturn(new Product(2L, "하의_청바지", 17000L, new Brand(2L, "청바지브랜드", null), null));

        var result = productService.getLowestProducts();

        assertNotNull(result);
        assertEquals(2, result.getProducts().size());
        assertEquals(49000, result.getTotalPrice());
    }

    List<ProductCategory> generateProductCategories() {
        var one = new ProductCategory();
        one.setId(1L);
        one.setMainName("상의");
        var two = new ProductCategory();
        two.setId(2L);
        two.setMainName("하의");

        return Arrays.asList(one, two);
    }
}