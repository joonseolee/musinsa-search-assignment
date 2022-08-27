package com.joonseolee.musinsasearchassignment.exception;

import com.joonseolee.musinsasearchassignment.model.ErrorStatusType;
import lombok.Getter;

@Getter
public class MusinsaException extends RuntimeException {
    private static final int DEFAULT_ERROR_CODE = -1;
    protected final int resultCode;

    public MusinsaException(ErrorStatusType errorStatusType) {
        super(errorStatusType.getResultMessage());
        this.resultCode = errorStatusType.getResultCode();
    }
    public MusinsaException(String errorMessage) {
        super(errorMessage);
        this.resultCode = DEFAULT_ERROR_CODE;
    }
}
