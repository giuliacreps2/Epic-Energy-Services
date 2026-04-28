package team5.Epic_Energy_Services.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record InvoiceRequestDTO(
        @NotNull(message = "La data è obbligatoria")
        LocalDate date,

        @NotNull(message = "L'importo è obbligatorio")
        @Min(value = 0, message = "L'importo non può essere negativo")
        double amount,

        @NotNull(message = "Il numero fattura è obbligatorio")
        int number,

        @NotNull(message = "L'ID del cliente è obbligatorio")
        Long customerId,

        @NotNull(message = "L'ID dello stato è obbligatorio")
        Long statusId
) {
}