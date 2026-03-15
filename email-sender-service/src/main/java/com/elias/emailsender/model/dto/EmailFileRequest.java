package com.elias.emailsender.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public record EmailFileRequest(@NotEmpty String[] toUser,
                               @NotBlank String subject,
                               @NotBlank String message,
                               MultipartFile file
) {
}
