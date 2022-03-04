package com.siteminder.mailservice.core.strategies;

import com.siteminder.mailservice.core.dto.MailRequest;

public interface MailProviderStrategy {
    /**
     * All mail providers will accept the mail request from the user input
     * <br/>Then will utilize Converters to build the specifics of the request
     * @return "Success" - TODO: to be implemented
     */
    String postMail(MailRequest request);
}
