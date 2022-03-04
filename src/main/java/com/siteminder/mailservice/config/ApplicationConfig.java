package com.siteminder.mailservice.config;

import com.siteminder.mailservice.core.gateway.MailGateway;
import com.siteminder.mailservice.core.handler.DefaultMailHandler;
import com.siteminder.mailservice.core.handler.MailHandler;
import com.siteminder.mailservice.core.strategies.Mailgun;
import com.siteminder.mailservice.core.strategies.Sendgrid;
import com.siteminder.mailservice.infrastructure.converter.MailgunConverter;
import com.siteminder.mailservice.infrastructure.converter.SendGridConverter;
import com.siteminder.mailservice.infrastructure.mailgun.MailgunConnection;
import com.siteminder.mailservice.infrastructure.mailgun.MailgunGateway;
import com.siteminder.mailservice.infrastructure.sendgrid.SendGridMailGateway;
import com.siteminder.mailservice.infrastructure.sendgrid.SendgridConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Qualifier("mailGunConn")
    @Bean
    public MailgunConnection mailGunConnection(RestTemplate restTemplate,
                                               MailgunProperties properties) {
        return new MailgunConnection(properties, restTemplate);
    }

    @Qualifier("mailgun")
    @Primary
    @Bean
    public MailGateway mailgunGateway(@Qualifier("mailGunConn") MailgunConnection connection, MailgunConverter converter) {
        return new MailgunGateway(connection, converter);
    }

    @ConditionalOnProperty(name = "mail.connection", havingValue = "gateway", matchIfMissing = true)
    @Bean
    public Mailgun mailgunStrategy(@Qualifier("mailgun") MailGateway mailGateway) {
        return new Mailgun(mailGateway);
    }

    @ConditionalOnProperty(name = "mail.connection", havingValue = "fake", matchIfMissing = true)
    @Bean
    public Mailgun fakeMailgunStrategy(@Qualifier("fakeMailGun") MailGateway mailGateway) {
        return new Mailgun(mailGateway);
    }

    @Qualifier("sendGridConn")
    @Bean
    SendgridConnection sendgridConnection(RestTemplate restTemplate,
                                          SendGridProperties properties) {
        return new SendgridConnection(properties, restTemplate);
    }

    @Qualifier("sendgrid")
    @Primary
    @Bean
    MailGateway sendGridGateway(@Qualifier("sendGridConn") SendgridConnection connection, SendGridConverter sendGridConverter) {
        return new SendGridMailGateway(connection, sendGridConverter);
    }

    @ConditionalOnProperty(name = "mail.connection", havingValue = "gateway", matchIfMissing = true)
    @Bean
    public Sendgrid sendgridStrategy(@Qualifier("sendgrid") MailGateway mailGateway) {
        return new Sendgrid(mailGateway);
    }

    @ConditionalOnProperty(name = "mail.connection", havingValue = "fake", matchIfMissing = true)
    @Bean
    public Sendgrid fakeSendgridStrategy(@Qualifier("fakeSendGrid") MailGateway mailGateway) {
        return new Sendgrid(mailGateway);
    }

    @Bean
    public MailHandler mailHandler(MailProperties properties, Mailgun mailgun, Sendgrid sendgrid) {
        return new DefaultMailHandler(properties, mailgun, sendgrid);
    }
}
