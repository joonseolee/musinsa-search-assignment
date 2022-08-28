package com.joonseolee.musinsasearchassignment.service;

import com.joonseolee.musinsasearchassignment.entity.Brand;
import com.joonseolee.musinsasearchassignment.entity.BrandMapper;
import com.joonseolee.musinsasearchassignment.entity.BrandMapperImpl;
import com.joonseolee.musinsasearchassignment.exception.MusinsaException;
import com.joonseolee.musinsasearchassignment.model.InsertedBrand;
import com.joonseolee.musinsasearchassignment.model.UpdatedBrand;
import com.joonseolee.musinsasearchassignment.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @InjectMocks
    private BrandService brandService;
    @Spy
    private BrandMapper brandMapper = new BrandMapperImpl();
    @Mock
    private BrandRepository brandRepository;

    @Test
    void givenEmpty_whenInsertBrand_thenThrowMusinsaException() {
        assertThrows(MusinsaException.class, () -> brandService.insertBrand(null));
    }

    @Test
    void givenNotEmptyObject_whenInsertBrand_thenSuccess() {
        var request = new InsertedBrand.Request("joo");
        when(brandRepository.save(any(Brand.class))).thenReturn(new Brand(1L, "joo", Collections.emptyList()));

        var result = brandService.insertBrand(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("joo", result.getName());
    }

    @Test
    void givenEmpty_whenUpdateBrand_thenThrowMusinsaException() {
        assertThrows(MusinsaException.class, () -> brandService.updateBrand(0L, null));
    }

    @Test
    void givenInvalidId_whenUpdateBrand_thenThrowMusinsaException() {
        var invalidId = 999L;
        var request = new UpdatedBrand.Request("kimm");
        when(brandRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(MusinsaException.class, () -> brandService.updateBrand(invalidId, request));
    }

    @Test
    void givenNotEmpty_whenUpdateBrand_thenSuccess() {
        var invalidId = 1L;
        var request = new UpdatedBrand.Request("kimm");
        when(brandRepository.findById(invalidId))
                .thenReturn(Optional.of(new Brand(1L, "kimm", Collections.emptyList())));

        brandService.updateBrand(invalidId, request);

        verify(brandRepository, times(1)).save(any(Brand.class));
    }

    @Test
    void givenValidId_whenDeleteBrand_thenSuccess() {
        doNothing().when(brandRepository).deleteById(1L);
        
        brandService.deleteBrand(1L);

        verify(brandRepository, times(1)).deleteById(anyLong());
    }
}