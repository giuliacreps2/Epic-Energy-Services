package team5.Epic_Energy_Services.payloads;

//public record LoginDTO(
//        @NotBlank(message = "Email is required")
//        @Email(message = "Invalid email format")
//        String email,
//        @NotBlank(message = "Password is required")
//        String password
//) {
//}
public record LoginDTO(String email, String password) {
}
