package com.siteminder.mailservice.infrastructure.mailgun;

import com.siteminder.mailservice.core.dto.MailRequest;
import com.siteminder.mailservice.core.gateway.MailGateway;
import com.siteminder.mailservice.infrastructure.converter.MailgunConverter;
import com.siteminder.mailservice.infrastructure.mailgun.dto.MailgunResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class MailgunGateway implements MailGateway {
    private final MailgunConnection connection;
    private final MailgunConverter converter;

    @Override
    public String sendMail(MailRequest request) {
        Optional<MailgunResponse> resp = connection.send(converter.convert(request));
        if (resp.isPresent()) {
            return resp.get().getMessage();
        } else {
            return "no message response";
        }
    }
}
