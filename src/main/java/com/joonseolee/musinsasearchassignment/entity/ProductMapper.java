package com.joonseolee.musinsasearchassignment.entity;

import com.joonseolee.musinsasearchassignment.config.CommonMapper;
import com.joonseolee.musinsasearchassignment.model.InsertedProduct;
import com.joonseolee.musinsasearchassignment.model.UpdatedProduct;
import org.mapstruct.*;

@Mapper(
        config = CommonMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ProductMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "productCategory", ignore = true)
    @Mapping(target = "id", ignore = true)
    Product toProduct(InsertedProduct.Request request);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "productCategoryId", source = "productCategory.id")
    @Mapping(target = "id", source = "id")
    InsertedProduct.Response toInsertedProductResponse(Product product);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "productCategory", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateProductFromUpdatedProductRequest(@MappingTarget Product product, UpdatedProduct.Request request);
}
