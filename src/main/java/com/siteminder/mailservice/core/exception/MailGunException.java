package com.siteminder.mailservice.core.exception;

import com.siteminder.mailservice.core.constant.CustomError;

public class MailGunException extends RuntimeException{
    protected String code;

    public MailGunException() {
        this.code = CustomError.DEFAULT_SERVER_ERROR.getCode();
    }

    public String getCode() {
        return code;
    }
    public MailGunException(CustomError error, String message) {
        super(error.getMessage()+"; "+message);
        this.code = error.getCode();
    }

    public MailGunException(CustomError error, Throwable cause) {
        super(error.getMessage(), cause);
        this.code = error.getCode();
    }
}
