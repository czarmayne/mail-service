package com.siteminder.mailservice.core.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Const {
    public static final String LOGREF = "logref";
    public static final String FIELD_ERROR_KEY = "fieldErrors";
    public static final String MIME_TYPE = "text/plain"; //FIXME: can be moved to props for future proofing
    public static final String PRIMARY = "PRIMARY";
    public static final String SECONDARY = "SECONDARY";
}
