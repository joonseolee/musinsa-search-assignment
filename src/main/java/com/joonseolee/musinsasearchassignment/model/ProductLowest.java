package com.joonseolee.musinsasearchassignment.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductLowest {

    @Getter
    @AllArgsConstructor
    public static class Response {
        private final List<BriefProduct> products;
        private final Long totalPrice;
    }

    @Getter
    @AllArgsConstructor
    public static class BriefProduct {
        private final String categoryName;
        private final String brandName;
        private final Long price;
    }
}
