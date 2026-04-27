package team5.Epic_Energy_Services.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team5.Epic_Energy_Services.entities.Municipality;
import team5.Epic_Energy_Services.entities.Province;
import team5.Epic_Energy_Services.exceptions.BadRequestException;
import team5.Epic_Energy_Services.repositories.AddressRepository;
import team5.Epic_Energy_Services.repositories.MunicipalityRepository;
import team5.Epic_Energy_Services.repositories.ProvinceRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


@Service
public class ImportService {

    private final AddressRepository addressRepository;
    private final MunicipalityRepository municipalityRepository;
    private  final ProvinceRepository provinceRepository;

    public ImportService(AddressRepository addressRepository, MunicipalityRepository municipalityRepository, ProvinceRepository provinceRepository) {
        this.addressRepository = addressRepository;
        this.municipalityRepository = municipalityRepository;
        this.provinceRepository = provinceRepository;
    }


    @Transactional
    public void importProvince(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BadRequestException("File vuoto");
        }
        try (Scanner scanner = new Scanner(file.getInputStream(), StandardCharsets.UTF_8)) {
            if (scanner.hasNextLine()) scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] columns = scanner.nextLine().split(";");
                if (columns.length < 2) continue;

                String sigla = columns[0].trim();
                String nome = columns[1].trim();

                if (provinceRepository.findBySigla(sigla).isEmpty()) {
                    Province p = new Province();
                    p.setSigla(sigla);
                    p.setName(nome);
                    provinceRepository.save(p);
                }
            }
        }
        catch (IOException e) {
            throw new BadRequestException("Errore lettura file");
        }
    }

}
