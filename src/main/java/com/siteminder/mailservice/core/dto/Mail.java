package com.siteminder.mailservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Mail {

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Invalid email format")
    //TODO: validate if we need to filter emails (e.g. regexp = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)*gmail.com\$")
    private String email;
}
