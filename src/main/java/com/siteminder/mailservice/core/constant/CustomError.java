package com.siteminder.mailservice.core.constant;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum CustomError {
    //Server Error
    DEFAULT_SERVER_ERROR("001500", "UNKNOWN","Generic Server Error."),
    DEFAULT_BAD_REQUEST_ERROR("001400", "BAD_REQUEST","Generic Bad Request Error."),
    VALIDATION_FAILED("002400", "VALIDATION_ERROR","Request Validation Failed"),


    MAIL_GATEWAY_ERROR("100400", "MAIL_SERVICE_ERROR","Error while trying to send an email"),

    MAILGUN_GATEWAY_ERROR("200400", "MAILGUN_ERROR","Error Mailgun unavailable"),
    SENDGRID_GATEWAY_ERROR("300400", "SENDGRID_ERROR","Error Sendgrid unavailable"),;

    private final String code;
    private final String gatewayCode;
    private final String message;

    CustomError(String code, String gatewayCode, String message) {
        this.code = code;
        this.gatewayCode = gatewayCode;
        this.message = message;
    }

    private static final Map<String, CustomError> map = new HashMap<>();

    static {
        for (CustomError customError : CustomError.values()) {
            map.put(customError.getCode(), customError);
        }
    }

    public static CustomError getCustomErrorByCode(String code){
        return map.get(code);
    }
}
