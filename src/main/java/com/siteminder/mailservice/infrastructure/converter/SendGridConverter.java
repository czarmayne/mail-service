package com.siteminder.mailservice.infrastructure.converter;

import com.siteminder.mailservice.core.constant.Const;
import com.siteminder.mailservice.core.dto.Mail;
import com.siteminder.mailservice.core.dto.MailRequest;
import com.siteminder.mailservice.infrastructure.sendgrid.dto.Personalizations;
import com.siteminder.mailservice.infrastructure.sendgrid.dto.SendGridRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SendGridConverter implements Converter<MailRequest, SendGridRequest> {
    @Override
    public SendGridRequest convert(MailRequest source) {
        return SendGridRequest.builder()
                .personalizations(createPersonalization(source))
                .from(createTo(source.getFrom()))
                .subject(source.getSubject())
                .content(createContent(source))
                .build();
    }

    private List<? extends SendGridRequest.Content> createContent(MailRequest source) {
        return Arrays.asList(SendGridRequest.Content.builder()
                .type(Const.MIME_TYPE)
                .value(source.getMessage())
                .build());
    }

    private List<? extends Personalizations> createPersonalization(MailRequest source) {
        return Arrays.asList(Personalizations.builder()
                .to(createTo(source.getRecipients()))
                .cc(createTo(source.getCc()))
                .bcc(createTo(source.getBcc()))
                .build());
    }

    private List<? extends Personalizations.To> createTo(Set<Mail> mailSet) {
        //TODO: name not yet supported
        return mailSet.stream().map(i -> Personalizations.To.builder()
                .email(i.getEmail())
                .build()).collect(Collectors.toList());

    }

    private Personalizations.To createTo(String mail) {
        //TODO: name not yet supported
        return Personalizations.To.builder()
                .email(mail)
                .build();
    }
}
