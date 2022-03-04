package com.siteminder.mailservice.infrastructure.sendgrid;

import com.siteminder.mailservice.core.dto.MailRequest;
import com.siteminder.mailservice.core.gateway.MailGateway;
import com.siteminder.mailservice.infrastructure.converter.SendGridConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("SendGridMailGateway")
@RequiredArgsConstructor
@Slf4j
public class SendGridMailGateway implements MailGateway {
    private final SendgridConnection connection;
    private final SendGridConverter converter;

    @Override
    public String sendMail(MailRequest request) {
        connection.send(converter.convert(request));
        return "Successfully Sent via SentGrid Provier";
    }
}
