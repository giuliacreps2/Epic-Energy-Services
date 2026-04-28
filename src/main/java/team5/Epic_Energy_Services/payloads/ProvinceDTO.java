package team5.Epic_Energy_Services.payloads;

import jakarta.validation.constraints.NotBlank;

public record ProvinceDTO(

        @NotBlank
        String sigla,

        @NotBlank
        String name) {
}
