package com.siteminder.mailservice.config;

import com.siteminder.mailservice.core.gateway.MailGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("fakeMailgun")
@Slf4j
@Configuration
public class MailgunConfig {

    @Qualifier("fakeMailGun")
    @Bean
    public MailGateway fakeSuccessfulMailgunConfig() {
        return (mailRequest) -> "Success";
    }

}
