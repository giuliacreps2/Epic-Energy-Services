package team5.Epic_Energy_Services.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team5.Epic_Energy_Services.entities.Municipality;
import team5.Epic_Energy_Services.services.MunicipalityService;

import java.util.List;

@RestController
@RequestMapping("/municipality")
public class MunicipalityController {

    private final MunicipalityService municipalityService;

    public MunicipalityController(MunicipalityService municipalityService) {
        this.municipalityService = municipalityService;
    }

    @GetMapping
    public Page<Municipality> findAllMunicipalities(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(defaultValue = "name") String sortBy) {
        return this.municipalityService.findAll(page, size, sortBy);
    }

    @GetMapping("/search")
    public List<Municipality> searchByName(@RequestParam String name) {
        return this.municipalityService.findByName(name);
    }

}
