package team5.Epic_Energy_Services.payloads;

import java.time.LocalDateTime;

public record ErrorsPayload(
        String message,
        LocalDateTime timestamp
) {
}