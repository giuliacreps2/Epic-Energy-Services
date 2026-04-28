package team5.Epic_Energy_Services.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team5.Epic_Energy_Services.entities.Province;
import team5.Epic_Energy_Services.repositories.ProvinceRepository;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }
}
