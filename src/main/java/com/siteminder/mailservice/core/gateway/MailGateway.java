package com.siteminder.mailservice.core.gateway;

import com.siteminder.mailservice.core.dto.MailRequest;

public interface MailGateway {
    String sendMail(MailRequest request);
}
