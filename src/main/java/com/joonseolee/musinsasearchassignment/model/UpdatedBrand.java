package com.joonseolee.musinsasearchassignment.model;

import com.joonseolee.musinsasearchassignment.config.Default;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdatedBrand {

    @Getter
    @NoArgsConstructor
    public static class Request {
        private String name;

        @Default
        public Request(String name) {
            this.name = name;
        }
    }
}
