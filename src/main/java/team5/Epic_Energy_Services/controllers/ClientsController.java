package team5.Epic_Energy_Services.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team5.Epic_Energy_Services.entities.B2bClient;
import team5.Epic_Energy_Services.payloads.ClientsDTO;
import team5.Epic_Energy_Services.services.ClientsService;

import java.util.UUID;

@RestController
@RequestMapping("/clients")
@Slf4j
public class ClientsController {

    private final ClientsService clientsService;

    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }


    @GetMapping("/hello")
    public String hello() {
        return "Controller attivo!";
    }

    //1.POST CLIENTE
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public B2bClient save(@RequestBody @Validated ClientsDTO body) {
        return this.clientsService.saveClient(body);
    }


    //5.PATCH COMPAGNY LOGO
    @PatchMapping("/{b2bClientId}/company-logo")
    public void updateCompanyLogo(@RequestParam("company_Logo") MultipartFile file, @PathVariable UUID b2bClientId) {
        log.info(file.getOriginalFilename());
        log.info(file.getContentType());
        log.info(String.valueOf(file.getSize()));

        this.clientsService.logoUpload(file, b2bClientId);
    }

}
