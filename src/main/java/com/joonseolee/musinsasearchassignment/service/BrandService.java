package com.joonseolee.musinsasearchassignment.service;

import com.joonseolee.musinsasearchassignment.entity.BrandMapper;
import com.joonseolee.musinsasearchassignment.exception.MusinsaException;
import com.joonseolee.musinsasearchassignment.model.ErrorStatusType;
import com.joonseolee.musinsasearchassignment.model.InsertedBrand;
import com.joonseolee.musinsasearchassignment.model.UpdatedBrand;
import com.joonseolee.musinsasearchassignment.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public InsertedBrand.Response insertBrand(InsertedBrand.Request request) {
        if (Objects.isNull(request)) {
            throw new MusinsaException(ErrorStatusType.NOT_FOUND_PARAMETERS_400);
        }
        var brand = brandMapper.toBrand(request);
        var savedBrand = brandRepository.save(brand);
        return brandMapper.toInsertedBrandResponse(savedBrand);
    }

    public void updateBrand(long id, UpdatedBrand.Request request) {
        if (Objects.isNull(request)) {
            throw new MusinsaException(ErrorStatusType.NOT_FOUND_PARAMETERS_400);
        }

        var brand = brandRepository.findById(id)
                .orElseThrow(() -> new MusinsaException(ErrorStatusType.NOT_FOUND_DATA_500));

        brand.setName(request.getName());
        brandRepository.save(brand);
    }

    public void deleteBrand(long id) {
        brandRepository.deleteById(id);
    }
}