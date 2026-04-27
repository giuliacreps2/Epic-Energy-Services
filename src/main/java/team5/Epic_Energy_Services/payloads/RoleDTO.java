package team5.Epic_Energy_Services.payloads;

import jakarta.validation.constraints.NotBlank;

public record RoleDTO(
        @NotBlank(message = "role can't be blank")
        String roleName
) {
}
