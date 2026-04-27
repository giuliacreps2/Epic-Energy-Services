package team5.Epic_Energy_Services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team5.Epic_Energy_Services.entities.B2bClient;

import java.util.UUID;

@Repository
public interface ClientsRepository extends JpaRepository<B2bClient, UUID> {

//    boolean existsById(UUID id);
}
