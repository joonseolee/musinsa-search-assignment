package com.joonseolee.musinsasearchassignment.repository;

import com.joonseolee.musinsasearchassignment.entity.Product;
import com.joonseolee.musinsasearchassignment.entity.ProductCategory;
import com.joonseolee.musinsasearchassignment.model.BrandLowest;
import com.joonseolee.musinsasearchassignment.model.QBrandLowest_Response;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

import static com.joonseolee.musinsasearchassignment.entity.QBrand.brand;
import static com.joonseolee.musinsasearchassignment.entity.QProduct.product;

@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Product findLowestByProductCategory(ProductCategory productCategory) {
        return findLowestOrHighestByProductCategory(productCategory, product.price.asc());
    }

    @Override
    public Product findHighestByProductCategory(ProductCategory productCategory) {
        return findLowestOrHighestByProductCategory(productCategory, product.price.desc());
    }

    @Override
    public BrandLowest.Response findLowestGroupByBrand() {
        return jpaQueryFactory
                .select(new QBrandLowest_Response(product.brand.name, product.price.sum()))
                .from(product)
                .groupBy(product.brand)
                .orderBy(product.price.sum().asc())
                .fetchFirst();
    }

    private Product findLowestOrHighestByProductCategory(ProductCategory productCategory, OrderSpecifier<Long> order) {
        return jpaQueryFactory
                .selectFrom(product)
                .join(product.brand, brand)
                .fetchJoin()
                .where(eqProductCategory(productCategory))
                .orderBy(order)
                .fetchFirst();
    }

    private BooleanExpression eqProductCategory(ProductCategory productCategory) {
        if (Objects.isNull(productCategory)) {
            return null;
        }

        return product.productCategory.eq(productCategory);
    }
}
