package com.siteminder.mailservice.config;

import com.siteminder.mailservice.core.gateway.MailGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("fakeSendGrid")
@Slf4j
@Configuration
public class SendGridConfig {
    @Qualifier("fakeSendGrid")
    @Bean
    @ConditionalOnProperty(name = "mail.connection", havingValue = "fake")
    public MailGateway fakeSuccessfulSendGridConfig() {
        return (mailRequest) -> "Success";
    }

}
