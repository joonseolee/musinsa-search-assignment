package com.joonseolee.musinsasearchassignment.repository;

import com.joonseolee.musinsasearchassignment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCustomRepository {
}
