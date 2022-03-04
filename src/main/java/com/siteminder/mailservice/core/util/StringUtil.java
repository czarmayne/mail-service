package com.siteminder.mailservice.core.util;

import com.siteminder.mailservice.core.dto.Mail;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

    public static String getListOfRecipients(Set<Mail> mailingList) {
        return Optional.ofNullable(mailingList.stream()
                        .map(Mail::getEmail)
                        .collect(Collectors.joining(", ")))
                .orElse("");
    }
}
