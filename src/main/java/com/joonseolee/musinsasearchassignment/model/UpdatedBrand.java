package com.joonseolee.musinsasearchassignment.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdatedBrand {

    @Getter
    @NoArgsConstructor
    public static class Request {
        private String name;

        public Request(String name) {
            this.name = name;
        }
    }
}
