package com.siteminder.factory

import com.siteminder.mailservice.core.dto.MailRequest

import java.time.LocalDateTime

class MailRequestFactory {

    static MailRequest defaultMailRequest(Map customAttrs = [:]) {
        def attrs = [
                recipients   : Arrays.asList("czar@gmail.com","mczarmayne@gmail.com"),
                cc           : Arrays.asList(""),
                bcc          : Arrays.asList(""),
                from         : "czar@gmail.com",
                message      : "test",
                subject      : "subj"
        ]

        attrs.putAll(customAttrs)
        new MailRequest(attrs)
    }
}
