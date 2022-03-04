package com.siteminder.mailservice.core.exception;

import com.siteminder.mailservice.core.constant.CustomError;

public class SendGridException extends RuntimeException{
    protected String code;

    public SendGridException() {
        this.code = CustomError.DEFAULT_SERVER_ERROR.getCode();
    }

    public String getCode() {
        return code;
    }
    public SendGridException(CustomError error, String message) {
        super(error.getMessage()+"; "+message);
        this.code = error.getCode();
    }

    public SendGridException(CustomError error, Throwable cause) {
        super(error.getMessage(), cause);
        this.code = error.getCode();
    }
}
