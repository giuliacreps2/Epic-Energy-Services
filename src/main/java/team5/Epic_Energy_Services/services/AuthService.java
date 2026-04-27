package team5.Epic_Energy_Services.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team5.Epic_Energy_Services.entities.User;
import team5.Epic_Energy_Services.exceptions.NotFoundException;
import team5.Epic_Energy_Services.exceptions.UnauthorizedException;
import team5.Epic_Energy_Services.payloads.LoginDTO;
import team5.Epic_Energy_Services.security.TokenTools;

@Service
public class AuthService {
    private final UsersService usersService;
    private final TokenTools tokenTools;
    private final PasswordEncoder bcrypt;

    public AuthService(UsersService usersService, TokenTools tokenTools, PasswordEncoder bcrypt) {

        this.usersService = usersService;
        this.tokenTools = tokenTools;
        this.bcrypt = bcrypt;
    }

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        try {
            User found = this.usersService.findByEmail(body.email());
            if (this.bcrypt.matches(body.password(), found.getPassword())) {
                return this.tokenTools.generateToken(found);

            } else {
                throw new UnauthorizedException("Credenziali errate");
            }
        } catch (NotFoundException ex) {
            throw new UnauthorizedException("Credenziali errate");
        }
    }
}
