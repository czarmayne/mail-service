package com.siteminder.mailservice.infrastructure.converter;

import com.siteminder.mailservice.core.dto.MailRequest;
import com.siteminder.mailservice.infrastructure.mailgun.dto.MailgunRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.siteminder.mailservice.core.util.StringUtil.getListOfRecipients;

/**
 * Translate the input from user to the API specs of MailGun Provider
 */
@Component
public class MailgunConverter implements Converter<MailRequest, MailgunRequest> {

    @Override
    public MailgunRequest convert(MailRequest source) {
        return MailgunRequest.builder()
                .from(source.getFrom())
                .subject(source.getSubject())
                .text(source.getMessage())
                .to(getListOfRecipients(source.getRecipients()))
                .cc(getListOfRecipients(source.getCc()))
                .bcc(getListOfRecipients(source.getBcc()))
                .build();
    }


}
