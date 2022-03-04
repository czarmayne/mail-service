package com.siteminder.mailservice.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {
    private static final int MILLISECONDS = 1000000;
    public static long getDuration(long startTime, long endTime) {
        return  (endTime - startTime) / MILLISECONDS;
    }
}
