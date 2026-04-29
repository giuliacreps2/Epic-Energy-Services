package team5.Epic_Energy_Services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team5.Epic_Energy_Services.entities.Municipality;

import java.util.List;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
    List<Municipality> findByNameContainingIgnoreCase(String name);

    List<Municipality> findByNameIgnoreCase(String nomeComune);
}
