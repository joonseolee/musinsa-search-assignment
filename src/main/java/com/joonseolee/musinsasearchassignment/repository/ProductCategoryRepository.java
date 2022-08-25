package com.joonseolee.musinsasearchassignment.repository;

import com.joonseolee.musinsasearchassignment.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
