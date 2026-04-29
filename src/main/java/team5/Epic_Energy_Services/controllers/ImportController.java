package team5.Epic_Energy_Services.controllers;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team5.Epic_Energy_Services.entities.Province;
import team5.Epic_Energy_Services.services.ImportService;
import team5.Epic_Energy_Services.services.ProvinceService;

@RestController
@RequestMapping("/api/import")
public class ImportController {

    private final ImportService importService;



    public ImportController(ImportService importService, ProvinceService provinceService) {
        this.importService = importService;
    }

    @PostMapping("/provinces")
    public ResponseEntity<String> uploadProvinces(
            @RequestParam("file") MultipartFile file) {

        importService.importProvince(file);
        return ResponseEntity.ok("Import completato");
    }

    @PostMapping("/municipality")
    public ResponseEntity<String> uploadMunicipality(
            @RequestParam("file") MultipartFile file) {

        importService.importMunicipality(file);
        return ResponseEntity.ok("Import completato");
    }


}
