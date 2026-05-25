package com.flightping.backend.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 특가
    DEAL_NOT_FOUND(HttpStatus.NOT_FOUND, "DEAL_NOT_FOUND", "특가 정보를 찾을 수 없습니다."),

    // 관심 노선
    ROUTE_ALREADY_EXISTS(HttpStatus.CONFLICT, "ROUTE_ALREADY_EXISTS", "이미 등록된 관심 노선입니다."),
    ROUTE_NOT_FOUND(HttpStatus.NOT_FOUND, "ROUTE_NOT_FOUND", "관심 노선을 찾을 수 없습니다."),

    // 찜
    SAVED_DEAL_ALREADY_EXISTS(HttpStatus.CONFLICT, "SAVED_DEAL_ALREADY_EXISTS", "이미 찜한 특가입니다."),
    SAVED_DEAL_NOT_FOUND(HttpStatus.NOT_FOUND, "SAVED_DEAL_NOT_FOUND", "찜한 특가를 찾을 수 없습니다."),

    // 서버
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "서버 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
