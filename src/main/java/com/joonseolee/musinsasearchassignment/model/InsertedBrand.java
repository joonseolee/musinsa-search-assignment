package com.joonseolee.musinsasearchassignment.model;

import com.joonseolee.musinsasearchassignment.config.Default;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InsertedBrand {

    @Getter
    @NoArgsConstructor
    public static class Request implements Serializable {
        private String name;
    }

    @Getter
    public static class Response implements Serializable {
        private final Long id;
        private final String name;

        @Default
        public Response(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}