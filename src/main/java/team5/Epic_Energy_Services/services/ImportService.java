package team5.Epic_Energy_Services.services;

import lombok.extern.slf4j.Slf4j;
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
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

@Slf4j
@Service
public class ImportService {

    private static final Map<String, String> PROVINCE_MAP = Map.ofEntries(
            Map.entry("Valle d'Aosta/Vallée d'Aoste", "Aosta"),
            Map.entry("Bolzano/Bozen", "Bolzano"),
            Map.entry("Forlì-Cesena", "Forli-Cesena"),
            Map.entry("Monza e della Brianza", "Monza-Brianza"),
            Map.entry("Reggio nell'Emilia", "Reggio-Emilia"),
            Map.entry("Reggio di Calabria", "Reggio-Calabria"),
            Map.entry("Pesaro e Urbino", "Pesaro-Urbino"),
            Map.entry("Vibo Valentia", "Vibo-Valentia"),
            Map.entry("Ascoli Piceno", "Ascoli-Piceno"),
            Map.entry("La Spezia", "La-Spezia"),
            Map.entry("Massa-Carrara", "Massa-Carrara")
    );
    private final AddressRepository addressRepository;
    private final MunicipalityRepository municipalityRepository;
    private final ProvinceRepository provinceRepository;

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
        } catch (IOException e) {
            throw new BadRequestException("Errore lettura file");
        }
    }

   /* @Transactional
    public void importMunicipality(MultipartFile file) {
        if (file.isEmpty()) throw new BadRequestException("File comuni vuoto");

        try (Scanner scanner = new Scanner(file.getInputStream(), StandardCharsets.UTF_8)) {
            if (scanner.hasNextLine()) scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) continue;

                String[] columns = line.split(";");
                if (columns.length < 4) continue;

                String nomeComune = columns[2].trim();
                String nomeProvinciaCSV = columns[3].trim();
                String nomeProvinciaCorretto = PROVINCE_MAP.getOrDefault(nomeProvinciaCSV, nomeProvinciaCSV);


                Province p = provinceRepository.findByName(nomeProvinciaCorretto).orElse(null);

                if (p != null && municipalityRepository.findByNameIgnoreCase(nomeComune).isEmpty()) {
                    Municipality m = new Municipality();
                    m.setName(nomeComune);
                    m.setProvince(p);
                    municipalityRepository.save(m);
                }

            }
        } catch (IOException e) {
            throw new BadRequestException("Errore lettura file comuni");
        }
    }*/

    @Transactional
    public void importMunicipality(MultipartFile file) {
        if (file.isEmpty()) throw new BadRequestException("File comuni vuoto");

        try (Scanner scanner = new Scanner(file.getInputStream(), StandardCharsets.UTF_8)) {
            if (scanner.hasNextLine()) {
                String header = scanner.nextLine();
                log.info("Header CSV: " + header);
            }

            int salvati = 0;
            int saltati = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) continue;

                String[] columns = line.split(";");
                log.info("Colonne trovate: " + columns.length + " → " + Arrays.toString(columns));
                if (columns.length < 4) {
                    saltati++;
                    continue;
                }

                String nomeComune = columns[2].trim();
                String nomeProvinciaCSV = columns[3].trim();
                String nomeProvinciaCorretto = PROVINCE_MAP.getOrDefault(nomeProvinciaCSV, nomeProvinciaCSV);

                Province p = provinceRepository.findByName(nomeProvinciaCorretto).orElse(null);

                if (p == null) {
                    log.warn("Provincia non trovata: " + nomeProvinciaCSV + " → " + nomeProvinciaCorretto);
                    saltati++;
                    continue;
                }

                if (municipalityRepository.findByNameIgnoreCase(nomeComune).isEmpty()) {
                    Municipality m = new Municipality();
                    m.setName(nomeComune);
                    m.setProvince(p);
                    municipalityRepository.save(m);
                    salvati++;
                }
            }

            log.info("Import completato → salvati: " + salvati + ", saltati: " + saltati);

        } catch (IOException e) {
            throw new BadRequestException("Errore lettura file comuni");
        }
    }


}
