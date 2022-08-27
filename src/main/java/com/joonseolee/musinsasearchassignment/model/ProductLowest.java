package com.joonseolee.musinsasearchassignment.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductLowest {

    @Getter
    @AllArgsConstructor
    public static class Response implements Serializable {
        private static final long serialVersionUID = 793084842018398620L;

        private final List<BriefProduct> products;
        private final Long totalPrice;
    }

    @Getter
    @AllArgsConstructor
    public static class BriefProduct implements Serializable {
        private static final long serialVersionUID = 5290377528729454742L;

        private final String categoryName;
        private final String brandName;
        private final Long price;
    }
}
