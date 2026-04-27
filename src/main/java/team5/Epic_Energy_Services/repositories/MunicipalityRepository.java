package team5.Epic_Energy_Services.repositories;

import team5.Epic_Energy_Services.entities.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipalityRepository extends JpaRepository <Municipality,Long> {
}
