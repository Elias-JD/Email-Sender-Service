package com.elias.emailsender.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record WelcomeRequest(@NotEmpty String[] toUser,
                             @NotBlank String subject,
                             @NotBlank String username,
                             @NotBlank String message
) {
}
