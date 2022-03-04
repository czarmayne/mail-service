package com.siteminder.mailservice.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HeadersUtil {
    public static final String BEARER = "Bearer ";

    public static HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    public static HttpHeaders getHeadersWithAuth(String token) {
        HttpHeaders headers = getHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, BEARER +
                token);
        return headers;
    }
}
