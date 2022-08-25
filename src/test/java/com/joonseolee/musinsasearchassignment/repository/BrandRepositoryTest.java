package com.joonseolee.musinsasearchassignment.repository;

import com.joonseolee.musinsasearchassignment.config.TestDataSourceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import(TestDataSourceConfig.class)
@DataJpaTest
class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    void givenNothing_whenFindAll_thenSuccess() {
        var brands = brandRepository.findAll();
        
        assertNotNull(brands);
        assertEquals(9, brands.size());
    }
}