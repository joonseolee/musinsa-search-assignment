package com.joonseolee.musinsasearchassignment.entity;

import com.joonseolee.musinsasearchassignment.config.CommonMapper;
import com.joonseolee.musinsasearchassignment.model.InsertedBrand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        config = CommonMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface BrandMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Brand toBrand(InsertedBrand.Request request);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", source = "id")
    InsertedBrand.Response toInsertedBrandResponse(Brand brand);
}