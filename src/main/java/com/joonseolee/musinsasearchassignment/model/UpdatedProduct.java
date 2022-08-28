package com.joonseolee.musinsasearchassignment.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdatedProduct {

    @Getter
    @NoArgsConstructor
    public static class Request {
        private String name;
        private Long price;
        private Long brandId;
        private Long productCategoryId;
    }
}
