package com.joonseolee.musinsasearchassignment.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductLowestHighest {

    @Getter
    @AllArgsConstructor
    public static class Response {
        private final String minBrandName;
        private final Long minPrice;
        private final String maxBrandName;
        private final Long maxPrice;
    }
}
