package com.booklist.app.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseBuilder {

    public static <T> ResponseEntity<ApiResponse<T>> build(
            HttpStatus status, String message, T data) {

        return ResponseEntity.status(status)
                .body(ApiResponse.<T>builder()
                        .success(status.is2xxSuccessful())
                        .message(message)
                        .data(data)
                        // .statusCode(status.value())
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
