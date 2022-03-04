package com.siteminder.mailservice.config;


import lombok.Data;

@Data
abstract class BaseProperties {
    private String apiKey;
    private String baseUrl;
    private String api;
}
