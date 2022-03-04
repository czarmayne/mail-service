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
public class Personalizations {
    private List<? extends To> cc;

    private List<? extends To> bcc;

    private List<? extends To> to;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class To {
        private String name;
        private String email;
    }
}
