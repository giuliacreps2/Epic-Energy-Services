package team5.Epic_Energy_Services;

import jakarta.validation.constraints.NotBlank;

public record RoleDTO(
        @NotBlank(message = "role can't be blank")
        String roleName
) {
}
