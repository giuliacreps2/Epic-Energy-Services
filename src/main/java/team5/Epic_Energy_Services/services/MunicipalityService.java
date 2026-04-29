package team5.Epic_Energy_Services.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team5.Epic_Energy_Services.entities.Municipality;
import team5.Epic_Energy_Services.repositories.MunicipalityRepository;

@Service
public class MunicipalityService {

    private final MunicipalityRepository municipalityRepository;

    public MunicipalityService(MunicipalityRepository municipalityRepository) {
        this.municipalityRepository = municipalityRepository;
    }

    public Page<Municipality> findAll(int page, int size, String sortBy) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.municipalityRepository.findAll(pageable);
    }


    public Municipality findByName(String name) {
        return this.municipalityRepository.findByNameContainingIgnoreCase(name);
    }
}
