package models.response;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token,
        String type
) {
}
