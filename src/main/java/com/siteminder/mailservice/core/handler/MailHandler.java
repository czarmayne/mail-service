package com.siteminder.mailservice.core.handler;

import com.siteminder.mailservice.core.dto.MailRequest;

public interface MailHandler {
    /**
     * Default implementation means that once retry has been maxed out for the primary provider
     * <br/> then it will try to route it to the secondary mail provider
     *
     * In the future, if plan to add X more, then consider initializing all the strategy of mail providers to a map
     * <br/> then loop if through once a failure has been encountered.
     *
     * @param request
     * @return
     */
    String sendMail(MailRequest request);
}
