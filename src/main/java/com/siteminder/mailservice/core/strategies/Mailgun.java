package com.siteminder.mailservice.core.strategies;

import com.siteminder.mailservice.core.dto.MailRequest;
import com.siteminder.mailservice.core.gateway.MailGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
public class Mailgun implements MailProviderStrategy {

    private final MailGateway mailGateway;

    @Override
    public String postMail(MailRequest request) {
        log.info("[START] Mailgun Strategy");
        mailGateway.sendMail(request);
        //TODO: to be removed, but if you want to simulate error, uncomment and comment the return statement
//        throw new RuntimeException("Simulate Error");
        return "Success Mailgun";
    }
}
