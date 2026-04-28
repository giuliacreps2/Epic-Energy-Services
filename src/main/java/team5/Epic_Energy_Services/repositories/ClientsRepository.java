package team5.Epic_Energy_Services.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team5.Epic_Energy_Services.entities.B2bClient;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientsRepository extends JpaRepository<B2bClient, UUID> {

    Page<B2bClient> findAllByCreatedAt(LocalDate createdAt, Pageable pageable);

    Page<B2bClient> findAllByLastContactDate(LocalDate lastContactDate, Pageable pageable);

    Page<B2bClient> findByContactNameContainingIgnoreCase(String contactName, Pageable pageable);

    Optional<B2bClient> findByContactNameIgnoreCase(String contactName);

    boolean existsByContactEmail(String contactEmail);
}
