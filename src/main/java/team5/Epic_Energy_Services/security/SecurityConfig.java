package team5.Epic_Energy_Services.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        httpSecurity.sessionManagement(
                s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.formLogin(f -> f.disable());
        httpSecurity.csrf(c->c.disable());
        httpSecurity.authorizeHttpRequests(
                req->req.requestMatchers("/**").permitAll());
        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder encoder(){
        return  new BCryptPasswordEncoder(12);
    }
}
