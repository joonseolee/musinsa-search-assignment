package com.joonseolee.musinsasearchassignment.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorStatusType {
    NOT_FOUND_PARAMETERS_400(400, "파라미터를 다시 확인해주세요."),
    NOT_WORKING_SERVER_500(500, "잠시후 다시 시도해주세요."),
    NOT_FOUND_DATA_500(500, "데이터를 찾을수없습니다."),
    UNKNOWN_999(999, "알수없는 에러가 발생했습니다."),
    ;

    private final int resultCode;
    private final String resultMessage;
}
