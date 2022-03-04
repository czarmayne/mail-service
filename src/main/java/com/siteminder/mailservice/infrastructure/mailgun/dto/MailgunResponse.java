package com.siteminder.mailservice.infrastructure.mailgun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailgunResponse {
    @Builder.Default
    private String id = "1234MockTestDate";
    @Builder.Default
    private String message = "Successfully sent via Mailgun";
}
