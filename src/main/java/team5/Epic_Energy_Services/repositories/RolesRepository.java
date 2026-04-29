package team5.Epic_Energy_Services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team5.Epic_Energy_Services.entities.Role;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RolesRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRoleName(String roleName);
    Optional<Role> findById(UUID id);
    boolean existsByRoleName(String roleName);
}