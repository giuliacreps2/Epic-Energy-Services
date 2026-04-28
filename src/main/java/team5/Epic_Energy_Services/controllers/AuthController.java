package team5.Epic_Energy_Services.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team5.Epic_Energy_Services.entities.User;
import team5.Epic_Energy_Services.exceptions.BadRequestException;
import team5.Epic_Energy_Services.payloads.LoginDTO;
import team5.Epic_Energy_Services.payloads.UsersDTO;
import team5.Epic_Energy_Services.payloads.UsersResponseDTO;
import team5.Epic_Energy_Services.services.AuthService;
import team5.Epic_Energy_Services.services.UsersService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UsersService usersService;

    public AuthController(AuthService authService, UsersService usersService) {
        this.authService = authService;
        this.usersService = usersService;
    }

    // POST /auth/register
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsersResponseDTO register(@RequestBody @Validated UsersDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            String errors = validation.getAllErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new BadRequestException("Dati non validi: " + errors);
        }
        User user = this.usersService.save(body);
        return new UsersResponseDTO(user.getId());
    }

    // POST /auth/login
    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginDTO body, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException("Credenziali non valide");
        return this.authService.checkCredentialsAndGenerateToken(body);
    }
}