package com.siteminder.mailservice.infrastructure.sendgrid;

import com.siteminder.mailservice.config.SendGridProperties;
import com.siteminder.mailservice.core.constant.CustomError;
import com.siteminder.mailservice.core.exception.SendGridException;
import com.siteminder.mailservice.infrastructure.sendgrid.dto.SendGridRequest;
import com.siteminder.mailservice.presentation.dto.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
public class SendgridConnection {
    private final SendGridProperties sendGridProperties;
    private final RestTemplate intraRestTemplate;

    public void send(SendGridRequest request) {
        log.info("[START] Send mail to SendGrid");
        try {
            var path = sendGridProperties.getBaseUrl() + sendGridProperties.getApiKey();
            ResponseEntity<ResponseWrapper<Void>> response = intraRestTemplate.exchange(
                    path,
                    HttpMethod.POST,
                    new HttpEntity<>(request),
                    new ParameterizedTypeReference<>() {
                    }
            );
            log.info("[END] Send mail to SendGrid {}", response.getStatusCode());
        } catch (Exception e) {
            log.error("Exception encountered while sending mail to Sendgrid", e);
            throw new SendGridException(CustomError.SENDGRID_GATEWAY_ERROR, e);
        }

    }

}
