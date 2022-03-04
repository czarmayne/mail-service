package com.siteminder.mailservice.infrastructure.mailgun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class MailgunRequest {

    private String from;
    private String to;
    private String subject;
    private String text;
    private String bcc;
    private String cc;
}
