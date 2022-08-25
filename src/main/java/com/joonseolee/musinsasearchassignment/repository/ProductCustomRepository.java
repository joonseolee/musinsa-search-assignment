package com.joonseolee.musinsasearchassignment.repository;

import com.joonseolee.musinsasearchassignment.entity.Product;
import com.joonseolee.musinsasearchassignment.entity.ProductCategory;
import com.joonseolee.musinsasearchassignment.model.BrandLowest;

public interface ProductCustomRepository {
    Product findLowestByProductCategory(ProductCategory productCategory);
    Product findHighestByProductCategory(ProductCategory productCategory);
    BrandLowest.Response findLowestGroupByBrand();
}
