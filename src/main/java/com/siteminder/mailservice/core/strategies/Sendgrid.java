package com.siteminder.mailservice.core.strategies;

import com.siteminder.mailservice.core.dto.MailRequest;
import com.siteminder.mailservice.core.gateway.MailGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class Sendgrid implements MailProviderStrategy{
    private final MailGateway mailGateway;

    //TODO: Consider how it will be handled if the secondary failed; will there be a retry process? kafka / db?
    @Override
    public String postMail(MailRequest request) {
        log.info("[START] Sendgrid Strategy");
        mailGateway.sendMail(request);
        return "Success Sendgrid";
    }
}
