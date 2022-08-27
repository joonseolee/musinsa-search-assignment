package com.joonseolee.musinsasearchassignment.model;

import com.joonseolee.musinsasearchassignment.exception.MusinsaException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseResponse<T> {
    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_TEXT = "success";

    private final Header header;
    private final T body;

    public BaseResponse(T body) {
        this.header = new Header(true, SUCCESS_CODE, SUCCESS_TEXT);
        this.body = body;
    }

    public BaseResponse(Header header, T body) {
        this.header = header;
        this.body = body;
    }

    public BaseResponse(ErrorStatusType statusType) {
        this.header = new Header(false, statusType.getResultCode(), statusType.getResultMessage());
        this.body = null;
    }

    public BaseResponse(HttpStatus httpStatus) {
        this.header = new Header(false, httpStatus.value(), httpStatus.getReasonPhrase());
        this.body = null;
    }

    public BaseResponse(MusinsaException exception) {
        this.header = new Header(false, exception.getResultCode(), exception.getMessage());
        this.body = null;
    }

    @AllArgsConstructor
    public static class Header {
        private final boolean isSuccessful;
        private final int resultCode;
        private final String resultMessage;

        public boolean getIsSuccessful() {
            return isSuccessful;
        }

        public int getResultCode() {
            return resultCode;
        }

        public String getResultMessage() {
            return resultMessage;
        }
    }
}
