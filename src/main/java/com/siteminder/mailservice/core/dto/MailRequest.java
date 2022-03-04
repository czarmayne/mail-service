package com.siteminder.mailservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MailRequest {

    //FIXME: initially used SET to filter out duplicates; Consider max limit for the mail providers, might need to create a custom validator
    @Valid
    private Set<Mail> recipients;
    private Set<Mail> cc;
    private Set<Mail> bcc;
    @NotBlank(message = "Parameter \"from\" should not be empty")
    @Email(message = "Parameter \"from\" should be valid Email")
    private String from;
    @NotBlank(message = "Must contain subject")
    private String subject;
    @NotBlank(message = "Must contain message body")
    private String message;

}
