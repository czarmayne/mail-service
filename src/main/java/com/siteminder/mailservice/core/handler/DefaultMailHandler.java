package com.siteminder.mailservice.core.handler;

import com.siteminder.mailservice.config.MailProperties;
import com.siteminder.mailservice.core.constant.CustomError;
import com.siteminder.mailservice.core.dto.MailRequest;
import com.siteminder.mailservice.core.exception.MailServiceException;
import com.siteminder.mailservice.core.strategies.MailProviderStrategy;
import com.siteminder.mailservice.core.strategies.Mailgun;
import com.siteminder.mailservice.core.strategies.Sendgrid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.siteminder.mailservice.core.constant.Const.PRIMARY;
import static com.siteminder.mailservice.core.constant.Const.SECONDARY;

@RequiredArgsConstructor
@Slf4j
public class DefaultMailHandler implements MailHandler {

    private final MailProperties mailProperties;
    private final Mailgun mailgun;
    private final Sendgrid sendgrid;
    private final Map<String, MailProviderStrategy> mailProviderStrategyMap = new HashMap<>();
    private MailProviderStrategy mailProviderStrategy;

    @Override
    public String sendMail(MailRequest request) {
        log.info("[START] Executing default mail handler");
        var response = "Hello, from handler "; //TODO: fix with correct response
        try {
            mapMailProvider();
            CompletableFuture<String> primaryMail = routeToPrimary(request);
            log.debug("Mail Response {}", primaryMail.get());
            response += primaryMail.get();
        } catch (MailServiceException | ExecutionException | InterruptedException e) {
            log.warn("Failed to deliver mail. For more info {}", e.getCause());
            response += routeToSecondary(request);
        }
        log.info("[END] Executing default mail handler");
        return response;
    }

    private void mapMailProvider() {
        mailProviderStrategyMap.put(PRIMARY, mailgun);
        mailProviderStrategyMap.put(SECONDARY, sendgrid);
    }

    //TODO: can prolly be changed to @Retryable
    private CompletableFuture<String> routeToPrimary(MailRequest request) {
        final Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        return CompletableFuture.supplyAsync(
                () -> {
                    addTraceability(copyOfContextMap);
                    int count = 0;
                    while (true) {
                        try {
                            mailProviderStrategy = mailProviderStrategyMap.get(PRIMARY);
                            return mailProviderStrategy.postMail(request);
                        } catch (Exception e) {
                            count = maxedOutRetry(count, e);
                            log.info("Problem encountered while trying to send an email. Retrying in "
                                    + mailProperties.getRetryWait() + " seconds");
                            waitToRetry(e);
                        }
                    }
                }
        );
    }

    private void waitToRetry(Exception e) {
        try {
            Thread.sleep(mailProperties.getRetryWait() * 1000L);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new MailServiceException(CustomError.MAIL_GATEWAY_ERROR, e.getCause());
        }
    }

    private int maxedOutRetry(int count, Exception e) {
        if (++count == mailProperties.getMaxRetry()) {
            throw new MailServiceException(CustomError.MAIL_GATEWAY_ERROR, e.getMessage());
        }
        return count;
    }

    private void addTraceability(Map<String, String> copyOfContextMap) {
        if (!ObjectUtils.isEmpty(copyOfContextMap)) {
            MDC.setContextMap(copyOfContextMap);
        }
    }

    private String routeToSecondary(MailRequest request) {
        mailProviderStrategy = mailProviderStrategyMap.get(SECONDARY);
        return mailProviderStrategy.postMail(request);
    }

}

