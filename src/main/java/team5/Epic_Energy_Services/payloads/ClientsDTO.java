package team5.Epic_Energy_Services.payloads;


import jakarta.validation.constraints.*;
import team5.Epic_Energy_Services.enums.TypeB2bClient;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClientsDTO(

        @NotBlank(message = "La ragione sociale è obbligatoria")
        String companyName,

        @NotNull(message = "La partita IVA è obbligatoria")
        @Positive(message = "La partita IVA deve essere un numero positivo")
        //controllo che abbia un tot di numeri
        Long vatNumber,

        LocalDate createdAt,
        LocalDate lastContactDate,

        @PositiveOrZero(message = "Il fatturato non può essere negativo")
        BigDecimal annualRevenue,

        @NotBlank(message = "La PEC è obbligatoria")
        @Email(message = "Formato PEC non valido")
        //controllo che contenga la chiocciola e il punto
        String certifiedEmail,

        @NotNull(message = "Il telefono è obbligatorio")
        //controllo sui numeri
        Long phoneClient,

        @NotBlank(message = "L'email del contatto è obbligatoria")
        @Email(message = "Formato email contatto non valido")
        String contactEmail,

        @NotBlank(message = "Il nome del contatto è obbligatorio")
        String contactName,

        @NotBlank(message = "Il cognome del contatto è obbligatorio")
        String contactSurname,

        //controllo sui numeri
        Long contactPhone,

        String companyLogo,
        TypeB2bClient typeClient,


        /// ///////////
        @NotBlank(message = "")
        String street,

        @NotNull
        Integer number,

        @NotBlank
        String locality,

        @NotNull
        Integer zipCode,

        @NotNull
        Long id
) {

}