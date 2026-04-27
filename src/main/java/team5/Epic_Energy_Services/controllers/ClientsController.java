package team5.Epic_Energy_Services.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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


    //5.PATCH COMPAGNY LOGO
    @PatchMapping("/{b2bClientId}/company-logo")
    public void updateCompanyLogo(@RequestParam("company_Logo") MultipartFile file, @PathVariable UUID b2bClientId) {
        log.info(file.getOriginalFilename());
        log.info(file.getContentType());
        log.info(String.valueOf(file.getSize()));

        this.clientsService.logoUpload(file, b2bClientId);
    }

}
