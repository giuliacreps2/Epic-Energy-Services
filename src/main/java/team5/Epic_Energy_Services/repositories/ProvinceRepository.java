package team5.Epic_Energy_Services.repositories;

import team5.Epic_Energy_Services.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province,Long> {
    Optional<Province> findBySigla(String sigla);
    Optional<Province> findByName(String name);
}
