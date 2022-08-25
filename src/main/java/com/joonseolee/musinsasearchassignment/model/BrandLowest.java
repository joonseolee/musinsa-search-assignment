package com.joonseolee.musinsasearchassignment.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandLowest {

    @Getter
    public static class Response {
        private final String brandName;
        private final Long totalPrice;

        @QueryProjection
        public Response(String brandName, Long totalPrice) {
            this.brandName = brandName;
            this.totalPrice = totalPrice;
        }
    }
}
