package com.joonseolee.musinsasearchassignment.controller;

import com.joonseolee.musinsasearchassignment.model.BaseResponse;
import com.joonseolee.musinsasearchassignment.model.InsertedBrand;
import com.joonseolee.musinsasearchassignment.model.UpdatedBrand;
import com.joonseolee.musinsasearchassignment.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/brands")
@RestController
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public BaseResponse<InsertedBrand.Response> insertBrand(@RequestBody InsertedBrand.Request request) {
        return new BaseResponse<>(brandService.insertBrand(request));
    }

    @PutMapping("/{id}")
    public BaseResponse<Void> updateBrand(@PathVariable long id, @RequestBody UpdatedBrand.Request request) {
        brandService.updateBrand(id, request);
        return BaseResponse.ok();
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteBrand(@PathVariable long id) {
        brandService.deleteBrand(id);
        return BaseResponse.ok();
    }
}
