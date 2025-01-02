package models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RefreshTokenRequest(
        @Size(min = 16, max = 36, message = "The refresh token must be between 16 and 36 characters")
        @NotBlank(message = "The refresh token is required")
        String refreshTokenRequest
) {
}
