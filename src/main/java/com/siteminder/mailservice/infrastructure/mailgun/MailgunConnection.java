package com.siteminder.mailservice.infrastructure.mailgun;

import com.siteminder.mailservice.config.MailgunProperties;
import com.siteminder.mailservice.core.constant.CustomError;
import com.siteminder.mailservice.core.exception.MailGunException;
import com.siteminder.mailservice.core.util.HeadersUtil;
import com.siteminder.mailservice.infrastructure.mailgun.dto.MailgunRequest;
import com.siteminder.mailservice.infrastructure.mailgun.dto.MailgunResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class MailgunConnection {
    private final MailgunProperties mailProperties;
    private final RestTemplate restTemplate;

    public Optional<MailgunResponse> send(MailgunRequest request) {
        log.info("[START] Send mail to Mailgun");
        try {
            var path = mailProperties.getBaseUrl() + mailProperties.getApi();
            log.debug("Path Url: {}", path);
            ResponseEntity<MailgunResponse> response = restTemplate.exchange(path, HttpMethod.POST, new HttpEntity<>(request, HeadersUtil.getHeadersWithAuth(mailProperties.getApiKey())), new ParameterizedTypeReference<>() {
            });
            log.info("[END] Send mail to Mailgun {}", response);
            return Optional.ofNullable(response.getBody());
            //TODO: proper error handling so retry process will only repeat error is connection issue
        } catch (Exception e) {
            log.error("Exception encountered while sending mail to Mailgun {}", e);
            throw new MailGunException(CustomError.MAILGUN_GATEWAY_ERROR, e);
        }
    }
}
