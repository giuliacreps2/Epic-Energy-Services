package team5.Epic_Energy_Services.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team5.Epic_Energy_Services.entities.Province;

import team5.Epic_Energy_Services.services.ProvinceService;

@RestController
@RequestMapping("/province")
public class ProvinceController {

    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }


    @GetMapping
    public Page<Province> findAllProvinces(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "name") String sortBy) {
        return this.provinceService.findAll(page, size, sortBy);
    }
}
