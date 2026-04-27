package team5.Epic_Energy_Services.dto;
import jakarta.validation.constraints.NotBlank;

public record ProvinceDTO(

        @NotBlank
        String sigla,

        @NotBlank
        String name) {
}
