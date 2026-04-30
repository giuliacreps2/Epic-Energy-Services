package team5.Epic_Energy_Services.payloads;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record InvoiceRequestDTO(
        @NotNull(message = "La data è obbligatoria")
        LocalDate date,
        @NotNull(message = "L'importo è obbligatorio")
        Double amount,
        @NotNull(message = "Il numero fattura è obbligatorio")
        Integer number,
        @NotNull(message = "L'ID cliente è obbligatorio")
        UUID customerId,
        @NotNull(message = "L'ID stato è obbligatorio")
        Long statusId
) {
}