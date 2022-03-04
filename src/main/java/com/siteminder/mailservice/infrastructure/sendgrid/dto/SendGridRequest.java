package com.siteminder.mailservice.infrastructure.sendgrid.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SendGridRequest {
    private List<? extends Personalizations> personalizations;

    private String subject;

    private Personalizations.To from;

    private List<? extends Content> content;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class Content {
        private String type;
        private String value;
    }
}
