package team5.Epic_Energy_Services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team5.Epic_Energy_Services.entities.Municipality;

import java.util.Optional;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
    Municipality findByNameContainingIgnoreCase(String name);

    Optional<Municipality> findByNameIgnoreCase(String nomeComune);
}
