package team5.Epic_Energy_Services.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team5.Epic_Energy_Services.services.ImportService;

@RestController
@RequestMapping("/api/import")
public class ImportController {

    private final ImportService importService;

    public ImportController(ImportService importService) {
        this.importService = importService;
    }

    @PostMapping("/provinces")
    public ResponseEntity<String> uploadProvinces(
            @RequestParam("file") MultipartFile file) {

        importService.importProvince(file);
        return ResponseEntity.ok("Import completato");
    }
}
