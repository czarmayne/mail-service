package com.siteminder.mailservice.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseWrapper<T> {

    private boolean result;
    private T response;
    private String statusCode;

    public ResponseWrapper(boolean result, T payload) {
        this.result = result;
        this.response = payload;
    }

}