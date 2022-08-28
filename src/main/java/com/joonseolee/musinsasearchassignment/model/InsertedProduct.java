package com.joonseolee.musinsasearchassignment.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InsertedProduct {

    @Getter
    @NoArgsConstructor
    public static class Request implements Serializable {
        private String name;
        private Long price;
        private Long brandId;
        private Long productCategoryId;
    }

    @Getter
    public static class Response implements Serializable {
        private final Long id;
        private final String name;
        private final Long price;
        private final Long brandId;
        private final Long productCategoryId;

        public Response(Long id, String name, Long price, Long brandId, Long productCategoryId) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.brandId = brandId;
            this.productCategoryId = productCategoryId;
        }
    }
}
